package com.borlok.transferservice.model;

import java.util.List;

/**
 * @author Erofeevskiy Yuriy
 */


public record UserResponse(
        List<String> emails,
        List<String> phones
) {
    public static UserResponse of(UserDto update) {
        return new UserResponse(update.emails(), update.phones());
    }
}
