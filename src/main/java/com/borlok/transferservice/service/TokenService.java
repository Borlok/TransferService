package com.borlok.transferservice.service;

import com.borlok.transferservice.model.AuthenticationRequest;
import com.borlok.transferservice.model.User;

/**
 * @author Erofeevskiy Yuriy
 */


public interface TokenService {
    void authenticate(User user, AuthenticationRequest authenticationRequest);
    String createToken(User user);
}
