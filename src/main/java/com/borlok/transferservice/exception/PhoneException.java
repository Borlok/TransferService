package com.borlok.transferservice.exception;

import com.borlok.transferservice.exception.user.UserException;

/**
 * @author Erofeevskiy Yuriy
 */


public class PhoneException extends UserException {
    public PhoneException(PhoneExceptionMessage message) {
        super(message.name());
    }
}
