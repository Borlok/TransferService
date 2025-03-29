package com.borlok.transferservice.exception.user;

/**
 * @author Erofeevskiy Yuriy
 */


public class AccountException extends UserException {
    public AccountException(AccountExceptionMessage message) {
        super(message.name());
    }
}
