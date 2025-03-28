package com.borlok.transferservice.model;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
public final class UserResponse {
    private final List<String> emails;
    private final List<String> phones;

    /**
     *
     */
    public UserResponse(
            List<String> emails,
            List<String> phones
    ) {
        this.emails = emails;
        this.phones = phones;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getEmails().stream().map(Email::getEmail).collect(Collectors.toList()),
                user.getPhones().stream().map(Phone::getPhone).collect(Collectors.toList()));
    }

    public List<String> emails() {
        return emails;
    }

    public List<String> phones() {
        return phones;
    }
}
