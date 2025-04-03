package com.borlok.transferservice.model;

import com.borlok.transferservice.exception.user.EmailException;
import com.borlok.transferservice.exception.user.EmailExceptionMessage;
import com.borlok.transferservice.exception.user.PhoneException;
import com.borlok.transferservice.exception.user.PhoneExceptionMessage;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
public final class UserSearchParameters {
    private final String name;
    private final String email;
    private final String phone;
    private final LocalDate dateOfBirth;
    private final Integer page;
    private final Integer size;

    public UserSearchParameters(String name,
                                String email,
                                String phone,
                                LocalDate dateOfBirth, Integer page, Integer size) {
        validateEmail(email);
        validatePhone(phone);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.size = size;
        this.page = page;
    }

    private void validateEmail(String email) {
        if (email == null) return;
        final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (!pattern.matcher(email).matches())
            throw new EmailException(EmailExceptionMessage.INVALID_EMAIL_FORMAT);
    }

    private void validatePhone(String phone) {
        if (phone == null) return;
        final String PHONE_REGEX = "^([78])\\d{10}$";
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        if (!pattern.matcher(phone).matches())
            throw new PhoneException(PhoneExceptionMessage.INVALID_PHONE_FORMAT);
    }

    public static UserSearchParameters of(String name, String email, String phone, LocalDate dateOfBirth, Integer page, Integer size) {
        return new UserSearchParameters(name, email, phone, dateOfBirth, page, size);
    }
}
