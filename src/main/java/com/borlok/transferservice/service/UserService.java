package com.borlok.transferservice.service;

import com.borlok.transferservice.model.User;
import com.borlok.transferservice.model.UserRequest;
import com.borlok.transferservice.model.UserSearchParameters;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Erofeevskiy Yuriy
 */


public interface UserService {
    User getByEmailOrPhoneNumber(String username);
    User update(Long userId, UserRequest userRequest);
    Page<User> findByParameters(UserSearchParameters userSearchParameters);
}
