package com.healthcare.account.service;

import com.healthcare.account.entity.Account;
import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.service.iservice.AccountStatusService;
import com.healthcare.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service @RequiredArgsConstructor
public class AccountStatusServiceImpl implements AccountStatusService {

    private final AccountRepository accountRepository;

    @Override
    public void toggleTwoFactor(String userId, Boolean status) throws AccountNotFoundException {
        // Check if the account exists
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("user ID " + userId));

        // Toggle the two-factor status
        account.setTwoFactorEnabled(status);
        accountRepository.save(account);
    }

    @Override
    public void toggleDeactivation(String userId, Boolean status) throws AccountNotFoundException {
        // Check if the account exists
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("user ID " + userId));

        // Toggle the deactivation status
        account.setAccountDeactivated(status);
        accountRepository.save(account);
    }


    @Override
    public void toggleLockout(String userId, Boolean status) throws AccountNotFoundException {
        // Check if the account exists
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("user ID " + userId));

        // Toggle the lockout status
        account.setAccountLocked(status);
        accountRepository.save(account);
    }

    @Override
    public void toggleEnabling(String userId, Boolean status) throws AccountNotFoundException {
        // Check if the account exists
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("user ID " + userId));

        // Toggle the enabling status
        account.setAccountEnabled(status);
        accountRepository.save(account);
    }

    @Override
    public void suspendAccount(String userId, boolean status) throws AccountNotFoundException {
        // Check if the account exists
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("user ID " + userId));

        // Suspend the account
        account.setAccountSuspended(status);
        accountRepository.save(account);
    }

    @Override
    public void addBanHour(String userId, Integer hour) throws AccountNotFoundException {
        // Check if the account exists
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("user ID " + userId));

        // Add the specified number of hours to the ban period
        account.setBanCount(account.getBanCount() + 1);
        if(account.getBanCount() > 3) {
            account.setAccountSuspended(true);
        }
        LocalDateTime time = LocalDateTime.now();
        if(account.getUnbanTime() != null && account.getUnbanTime().isAfter(time)) {
            time = account.getUnbanTime();
        }
        time = time.plusHours(hour);
        account.setUnbanTime(time);
        accountRepository.save(account);
    }
}
