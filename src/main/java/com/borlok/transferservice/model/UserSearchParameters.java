package com.borlok.transferservice.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
public final class UserSearchParameters {
    private final String name;
    private final String email;
    private final String phone;
    private final LocalDate dateOfBirth;

    public UserSearchParameters(String name,
                                String email,
                                String phone,
                                LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public static UserSearchParameters of(String name, String email, String phone, LocalDate dateOfBirth) {
        return new UserSearchParameters(name, email, phone, dateOfBirth);
    }
}
