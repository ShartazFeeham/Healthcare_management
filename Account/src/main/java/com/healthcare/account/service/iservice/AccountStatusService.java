package com.healthcare.account.service.iservice;

import com.healthcare.account.exception.AccountNotFoundException;

public interface AccountStatusService {
    // User controllable
    void toggleTwoFactor(String userId, Boolean status) throws AccountNotFoundException;
    void toggleDeactivation(String userId, Boolean status) throws AccountNotFoundException;

    // Controlled by server
    void suspendAccount(String userId) throws AccountNotFoundException;
    void addBanHour(String userId, Integer hour) throws AccountNotFoundException;
    void toggleLockout(String userId, Boolean action) throws AccountNotFoundException;
    void toggleEnabling(String userId, Boolean action) throws AccountNotFoundException;
}
