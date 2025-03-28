package com.borlok.transferservice.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
public final class UserDto {
    private final String name;
    private final LocalDate dateOfBirth;
    private final List<String> emails;
    private final List<String> phones;

    public static UserDto of(User user, List<Email> emails, List<Phone> phones) {
        return new UserDto(
                user.getName(),
                user.getDateOfBirth(),
                emails.stream().map(Email::getEmail).collect(Collectors.toList()),
                phones.stream().map(Phone::getPhone).collect(Collectors.toList()));
    }

}
