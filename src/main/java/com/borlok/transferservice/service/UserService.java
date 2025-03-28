package com.borlok.transferservice.service;

import com.borlok.transferservice.model.User;
import com.borlok.transferservice.model.UserDto;
import com.borlok.transferservice.model.UserRequest;
import com.borlok.transferservice.model.UserSearchParameters;

import java.util.List;
import java.util.Optional;

/**
 * @author Erofeevskiy Yuriy
 */


public interface UserService {
    User getByEmailOrPhoneNumber(String username);
    User update(Long userId, UserRequest userRequest);
    List<User> findByParameters(Long userId, UserSearchParameters userSearchParameters);
}
