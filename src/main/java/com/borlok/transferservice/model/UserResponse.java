package com.borlok.transferservice.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public final class UserResponse {
    private final String name;
    private final String dateOfBirth;
    private final List<String> emails;
    private final List<String> phones;

    public UserResponse(
            String name,
            LocalDate birthDate,
            List<String> emails,
            List<String> phones
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.name = name;
        this.dateOfBirth = formatter.format(birthDate);
        this.emails = emails;
        this.phones = phones;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getName(), user.getDateOfBirth(), user.getEmails().stream().map(Email::getEmail).collect(Collectors.toList()),
                user.getPhones().stream().map(Phone::getPhone).collect(Collectors.toList()));
    }

    public static List<UserResponse> of(List<User> users) {
        return users.stream().map(UserResponse::of).collect(Collectors.toList());
    }
}
