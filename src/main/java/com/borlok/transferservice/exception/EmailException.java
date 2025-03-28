package com.borlok.transferservice.exception;

import com.borlok.transferservice.exception.user.UserException;

/**
 * @author Erofeevskiy Yuriy
 */


public class EmailException extends UserException {
    public EmailException(EmailExceptionMessage message) {
        super(message.name());
    }
}
