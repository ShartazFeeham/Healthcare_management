package com.healthcare.account.service.iservice;

import com.healthcare.account.exception.AccountNotFoundException;
import com.healthcare.account.exception.DuplicateEntityException;
import com.healthcare.account.model.AccountCreateDTO;
import com.healthcare.account.model.AccountReadDTO;
import com.healthcare.account.model.AdminAccountCreateDTO;

public interface AccountManagementService {
    void createAccount(AccountCreateDTO accountCreateDTO) throws DuplicateEntityException;
    AccountReadDTO getUserInfo(String identity) throws AccountNotFoundException;
    void createAdminAccount(AdminAccountCreateDTO createAdminDTO) throws DuplicateEntityException;
}
