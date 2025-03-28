package com.borlok.transferservice.exception.auth;

/**
 * @author Erofeevskiy Yuriy
 */

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(AuthenticationExceptionMessage message) {
        super(message.name());
    }
}
