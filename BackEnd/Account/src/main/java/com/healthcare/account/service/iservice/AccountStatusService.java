package com.healthcare.account.service.iservice;

import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.model.ReadForListDTO;

public interface AccountStatusService {
    // User controllable
    void toggleTwoFactor(String userId, Boolean status) throws AccountNotFoundException;
    ReadForListDTO toggleDeactivation(String userId, Boolean status) throws AccountNotFoundException;

    // Controlled by server
    void suspendAccount(String userId, boolean status) throws AccountNotFoundException;
    void addBanHour(String userId, Integer hour) throws AccountNotFoundException;
    void toggleLockout(String userId, Boolean action) throws AccountNotFoundException;
    void toggleEnabling(String userId, Boolean action) throws AccountNotFoundException;
}
