package com.borlok.transferservice;

import com.borlok.transferservice.model.Account;
import com.borlok.transferservice.model.Email;
import com.borlok.transferservice.model.Phone;
import com.borlok.transferservice.model.User;
import com.borlok.transferservice.rest.it.model.TestAuthData;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Erofeevskiy Yuriy
 */


public class Utils {
    public static User TestUser(String name) {
        User user = new User();
        user.setName(name);
        user.setPassword("$2a$10$.7dAMt6Ky4kwy1eRAL0x9um6.wK7NARJ5oYujLbsLHSqnYke6O.k.");
        user.setDateOfBirth(LocalDate.now());
        return user;
    }

    public static Account TestAccount(User user) {
        Account account = new Account();
        account.setUser(user);
        account.setInitialBalance(new BigDecimal("1000"));
        account.setBalance(new BigDecimal("1000"));
        return account;
    }

    public static Email TestEmail(User user) {
        Email email = new Email();
        email.setUser(user);
        email.setEmail(user.getName() + "@test.ts");
        return email;
    }

    public static Phone TestPhone(User user, String number) {
        Phone phone = new Phone();
        phone.setUser(user);
        phone.setPhone(number);
        return phone;
    }

    public static TestAuthData TestAuth(String username, String password) {
        return new TestAuthData(username, password);
    }
}
