package com.borlok.transferservice.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Erofeevskiy Yuriy
 */


public record UserDto (
        String name,
        LocalDateTime dateOfBirth,
        List<String> emails,
        List<String> phones
){
    public static UserDto of(User user, List<Email> emails, List<Phone> phones) {
        return new UserDto(
                user.getName(),
                user.getDateOfBirth(),
                emails.stream().map(Email::getEmail).toList(),
                phones.stream().map(Phone::getPhone).toList());
    }
}
