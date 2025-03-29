package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.exception.user.AccountException;
import com.borlok.transferservice.exception.user.AccountExceptionMessage;
import com.borlok.transferservice.model.Account;
import com.borlok.transferservice.repository.AccountRepository;
import com.borlok.transferservice.service.AccountService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */
@Slf4j
@Data
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Account getByUserId(Long userId) {
        log.info("getByUserId {}", userId);
        return accountRepository.findByUserId(userId).orElseThrow(() -> new AccountException(AccountExceptionMessage.ACCOUNT_NOT_FOUND));
    }

    @Override
    public void extractFrom(Account sender, BigDecimal value) {
        log.info("Extraction from account with id {}", sender.getId());
        sender.setBalance(sender.getBalance().subtract(value));
        accountRepository.save(sender);
    }

    @Override
    public void addTo(Account recipient, BigDecimal value) {
        log.info("Adding to account with id {}", recipient.getId());
        recipient.setBalance(recipient.getBalance().add(value));
        accountRepository.save(recipient);
    }

    @Scheduled(fixedRate = 30000)
    public void updateBalances() {
        log.info("Updating balances");
        String sql = "UPDATE accounts SET balance = balance * 1.10 WHERE balance < (initial_balance * 2.07)";
        jdbcTemplate.update(sql);
    }
}
