package com.healthcare.account.service;

import com.healthcare.account.entity.Account;
import com.healthcare.account.exception.*;
import com.healthcare.account.network.EmailSender;
import com.healthcare.account.service.iservice.AccessService;
import com.healthcare.account.model.LoginRequestDTO;
import com.healthcare.account.model.LoginResponseDTO;
import com.healthcare.account.repository.AccountRepository;
import com.healthcare.account.utilities.token.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service @RequiredArgsConstructor
public class AccessServiceImpl implements AccessService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    @Override
    public Account findByIdentity(String identity) {
        Optional<Account> accountOp = accountRepository.findByEmail(identity);
        if (accountOp.isEmpty()) accountOp = accountRepository.findById(identity);
        if(accountOp.isEmpty()) throw new AccountNotFoundException(identity);
        return accountOp.get();
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginDTO) throws AccountNotFoundException, PasswordMismatchException, AccountLockedException {
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
            if(loginDTO.getOtp() == otp){
                return generateLoginResponse(account);
            }
            else throw new OTPValidationException(loginDTO.getOtp());
        }

        if(account.isAccountLocked()){
            throw new AccountLockoutException();
        }
        if(account.isTwoFactorEnabled()){
            if(account.getOtpGenerationTime().isBefore(LocalDateTime.now().minusMinutes(5))) {
                generateOTP(loginDTO.getIdentity());
            }
            throw new TwoFactorException(account.getEmail());
        }

        return generateLoginResponse(account);
    }

    private LoginResponseDTO generateLoginResponse(Account account){
        LoginResponseDTO response = new LoginResponseDTO();
        response.setEmail(account.getEmail());
        response.setUserId(account.getUserId());
        response.setRole(account.getRole().toString());

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + account.getRole().toString());
        String token = JWTUtils.generateToken(account.getUserId(), roles);
        response.setBearerToken(token);

        if(account.isAccountLocked()){
            account.setAccountLocked(false);
            accountRepository.save(account);
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

        emailSender.send(account.getEmail(), "Health app OTP Token", "Your OTP is " + otp + ". Use it to gain access to your account.");
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
