package com.borlok.transferservice.exception.user;

/**
 * @author Erofeevskiy Yuriy
 */


public class EmailException extends UserException {
    public EmailException(EmailExceptionMessage message) {
        super(message.name());
    }
}
