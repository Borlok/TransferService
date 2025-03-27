package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.service.AccountService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Erofeevskiy Yuriy
 */
@Slf4j
@Data
@Service
public class AccountServiceImpl implements AccountService {
    private JdbcTemplate jdbcTemplate;

//    @Scheduled(fixedRate = 30000) // Каждые 30 секунд
//    public void updateBalances() {
//        String sql = "UPDATE account SET balance = balance * 1.10 WHERE balance < (balance * 2.07)";
//        jdbcTemplate.update(sql);
//    }

}
