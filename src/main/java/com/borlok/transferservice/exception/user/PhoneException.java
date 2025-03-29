package com.borlok.transferservice.exception.user;

/**
 * @author Erofeevskiy Yuriy
 */


public class PhoneException extends UserException {
    public PhoneException(PhoneExceptionMessage message) {
        super(message.name());
    }
}
