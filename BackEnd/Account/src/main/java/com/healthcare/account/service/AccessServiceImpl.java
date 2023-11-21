package com.healthcare.account.service;

import com.healthcare.account.entity.Account;
import com.healthcare.account.entity.Role;
import com.healthcare.account.exception.*;
import com.healthcare.account.model.ReadForListDTO;
import com.healthcare.account.model.UserMinimalInfoDTO;
import com.healthcare.account.network.DeviceInfoSender;
import com.healthcare.account.network.EmailSender;
import com.healthcare.account.repository.AdminAccountRepository;
import com.healthcare.account.service.iservice.AccessService;
import com.healthcare.account.model.LoginRequestDTO;
import com.healthcare.account.model.LoginResponseDTO;
import com.healthcare.account.repository.AccountRepository;
import com.healthcare.account.utilities.token.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class AccessServiceImpl implements AccessService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final DeviceInfoSender deviceInfoSender;
    private final AdminAccountRepository adminAccountRepository;

    @Override
    public Account findByIdentity(String identity) {
        Optional<Account> accountOp = accountRepository.findByEmail(identity);
        if (accountOp.isEmpty()) accountOp = accountRepository.findById(identity);
        if(accountOp.isEmpty()) throw new AccountNotFoundException(identity);
        return accountOp.get();
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginDTO) throws AccountNotFoundException, PasswordMismatchException {
        Account account = findByIdentity(loginDTO.getIdentity());

        if(!passwordEncoder.matches(loginDTO.getPassword(), account.getPassword())){
            throw new PasswordMismatchException();
        }

        if(account.isAccountSuspended()){
            throw new AccountSuspensionException();
        }

        if (account.getUnbanTime() != null && account.getUnbanTime().isAfter(LocalDateTime.now())) {
            LocalDateTime unbanTime = account.getUnbanTime();
            LocalDateTime currentTime = LocalDateTime.now();

            Duration duration = Duration.between(currentTime, unbanTime);
            int remainingHours = (int) duration.toHours();

            throw new AccountBanException(remainingHours);
        }

        int otp = 0;
        if(account.getOtp() != null) {
            otp = account.getOtp();
        }
        if(loginDTO.getOtp() != null && loginDTO.getOtp() != 0){
            if(account.getOtpGenerationTime().isBefore(LocalDateTime.now().minusMinutes(5))){
                throw new OTPExpiredException(loginDTO.getOtp());
            }
            if(loginDTO.getOtp() == otp){
                return generateLoginResponse(account, loginDTO.getDeviceCode());
            }
            else throw new OTPValidationException(loginDTO.getOtp());
        }

        if(account.isAccountLocked()){
            throw new AccountLockoutException();
        }
        if(account.isTwoFactorEnabled()){
            if(account.getOtpGenerationTime().isBefore(LocalDateTime.now().minusMinutes(5))) {
                generateOTP(loginDTO.getIdentity());
                throw new TwoFactorException(account.getEmail());
            }
        }

        return generateLoginResponse(account, loginDTO.getDeviceCode());
    }

    private LoginResponseDTO generateLoginResponse(Account account, String deviceCode){
        LoginResponseDTO response = new LoginResponseDTO();
        response.setEmail(account.getEmail());
        response.setUserId(account.getUserId());
        response.setRole(account.getRole().toString());

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + account.getRole().toString());
        String token = JWTUtils.generateToken(account.getUserId(), roles);
        response.setBearerToken(token);

        if(deviceCode != null){
            deviceInfoSender.send(account.getUserId(), account.getEmail(), deviceCode);
        }

        if(account.isAccountLocked()){
            account.setAccountLocked(false);
            accountRepository.save(account);
        }

        if(account.isAccountDeactivated()){
            throw new AccountLockoutException("Your account is deactivated. An admin will check you account and verify, then you can login.");
        }

        return response;
    }

    @Override
    public void generateOTP(String identity) throws AccountNotFoundException {
        Account account = findByIdentity(identity);

        Random random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        account.setOtp(otp);
        account.setOtpGenerationTime(LocalDateTime.now());
        accountRepository.save(account);

        emailSender.send(account.getEmail(), "Health app OTP Token",
                "Your OTP is " + otp + ". Use it to gain access to your account.");
    }

    @Override
    public String generateInternalToken() throws AccessDeniedException {
        return JWTUtils.generateInternalToken();
    }

    @Override
    public Boolean checkEmailAvailability(String email) {
        return accountRepository.findByEmail(email).isEmpty();
    }

    @Override
    public List<ReadForListDTO> getDoctors(int page, int size) {
        Page<Account> doctorsPage = accountRepository.findByRole(Role.DOCTOR, PageRequest.of(page, size));

        return doctorsPage.getContent().stream()
                .map(this::convertToDoctorsReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadForListDTO> getPatients(int page, int size) {
        Page<Account> doctorsPage = accountRepository.findByRole(Role.PATIENT, PageRequest.of(page, size));

        return doctorsPage.getContent().stream()
                .map(this::convertToDoctorsReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserMinimalInfoDTO getMinimalInfo(String userId) {
        var result = adminAccountRepository.findById(userId);
        if(result.isEmpty()) throw new AccountNotFoundException(userId);
        UserMinimalInfoDTO minimalInfo = new UserMinimalInfoDTO();
        minimalInfo.setPhotoURL(null);
        minimalInfo.setFirstName(result.get().getFirstName());
        minimalInfo.setLastName(result.get().getLastName());
        return minimalInfo;
    }

    @Override
    public String getEmail(String userId) {
        Optional<Account> accountOp = accountRepository.findById(userId);
        return accountOp.map(Account::getEmail).orElse(null);
    }

    private ReadForListDTO convertToDoctorsReadDTO(Account account) {
        return ReadForListDTO.builder()
                .userId(account.getUserId())
                .email(account.getEmail())
                .registerDate(account.getRegisterDate())
                .deactivation(account.isAccountDeactivated())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = findByIdentity(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(account.getRole().toString()));
        return new User(account.getUserId(),
                account.getPassword(),
                account.isAccountEnabled() && ! account.isAccountDeactivated(),
                ! account.isAccountSuspended(),
                true,
                ! account.isAccountLocked(),
                roles);
    }
}
