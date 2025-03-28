package com.borlok.transferservice.exception.user;

/**
 * @author Erofeevskiy Yuriy
 */


public class UserException extends RuntimeException {
    public UserException(UserExceptionMessage message) {
        super(message.name());
    }

    public UserException(String name) {
        super(name);
    }
}
