package com.borlok.transferservice.exception;

/**
 * @author Erofeevskiy Yuriy
 */


public class EmailException extends RuntimeException {
    public EmailException(EmailExceptionMessage message) {
        super(message.name());
    }
}
