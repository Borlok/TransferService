package com.borlok.transferservice.service;

import com.borlok.transferservice.model.Account;

import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */


public interface AccountService {
    Account getByUserId(Long userId);
    void extractFrom(Account sender, BigDecimal value);
    void addTo(Account recipient, BigDecimal value);
}
