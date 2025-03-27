package com.borlok.transferservice.exception;

/**
 * @author Erofeevskiy Yuriy
 */


public class PhoneException extends RuntimeException {
    public PhoneException(PhoneExceptionMessage message) {
        super(message.name());
    }
}
