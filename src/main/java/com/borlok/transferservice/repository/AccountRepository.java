package com.borlok.transferservice.repository;

import com.borlok.transferservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Erofeevskiy Yuriy
 */


public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserId(Long userId);
}
