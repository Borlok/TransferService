package com.borlok.transferservice.model;

import com.borlok.transferservice.exception.EmailException;
import com.borlok.transferservice.exception.EmailExceptionMessage;
import com.borlok.transferservice.exception.PhoneException;
import com.borlok.transferservice.exception.PhoneExceptionMessage;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Erofeevskiy Yuriy
 */


public record UserRequest (
        List<String> emails,
        List<String> phones) {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_REGEX = "^([78])\\d{10}$";

    public UserRequest {
        validateEmails(emails);
        validatePhones(phones);
    }

    private void validateEmails(List<String> emails) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (emails.isEmpty())
            throw new EmailException(EmailExceptionMessage.EMAILS_SHOULD_BE_MORE_THAN_ZERO);
        emails.forEach(email -> {
            if (!pattern.matcher(email).matches())
                throw new EmailException(EmailExceptionMessage.INVALID_EMAIL_FORMAT);
        });
    }

    private void validatePhones(List<String> phones) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        if (phones.isEmpty())
            throw new PhoneException(PhoneExceptionMessage.PHONES_SHOULD_BE_MORE_THAN_ZERO);
        phones.forEach(phone -> {
            if (!pattern.matcher(phone).matches())
                throw new PhoneException(PhoneExceptionMessage.INVALID_PHONE_FORMAT);
        });
    }
}
