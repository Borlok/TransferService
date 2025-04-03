package com.borlok.transferservice.security;

import com.borlok.transferservice.model.AuthenticationRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Erofeevskiy Yuriy
 */


public interface TokenService {
    String createToken(AuthenticationRequest authData, BCryptPasswordEncoder passwordEncoder);
    Authentication authenticate(String token);
    boolean isValid(String token);
    String getUserId(String token);
}
