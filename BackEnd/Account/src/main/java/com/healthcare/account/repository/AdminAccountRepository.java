package com.healthcare.account.repository;

import com.healthcare.account.entity.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, String> {
    long countByUserIdStartingWith(String idPattern);
}
