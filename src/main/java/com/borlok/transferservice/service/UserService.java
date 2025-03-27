package com.borlok.transferservice.service;

import com.borlok.transferservice.model.User;
import com.borlok.transferservice.model.UserDto;
import com.borlok.transferservice.model.UserRequest;

import java.util.Optional;

/**
 * @author Erofeevskiy Yuriy
 */


public interface UserService {
    Optional<User> getByEmailOrPhoneNumber(String username);
    UserDto update(Long userId, UserRequest userRequest);
}
