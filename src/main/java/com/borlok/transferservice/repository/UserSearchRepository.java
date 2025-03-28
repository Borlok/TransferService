package com.borlok.transferservice.repository;

import com.borlok.transferservice.model.User;
import com.borlok.transferservice.model.UserSearchParameters;

import java.util.List;

/**
 * @author Erofeevskiy Yuriy
 */


public interface UserSearchRepository {
    List<User> findAllByParameters(Long userId, UserSearchParameters userSearchParameters);
}
