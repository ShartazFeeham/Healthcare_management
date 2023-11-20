package com.healthcare.account.repository;

import com.healthcare.account.entity.Account;
import com.healthcare.account.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);
    Page<Account> findByRole(Role role, Pageable pageable);
}
