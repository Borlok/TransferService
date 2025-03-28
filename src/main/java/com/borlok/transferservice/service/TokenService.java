package com.borlok.transferservice.service;

import com.borlok.transferservice.model.AuthenticationRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Erofeevskiy Yuriy
 */


public interface TokenService {
    String createToken(AuthenticationRequest authData, BCryptPasswordEncoder passwordEncoder);
    Authentication authenticate(String token);
}
