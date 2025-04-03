package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.model.AuthenticationRequest;
import com.borlok.transferservice.service.AuthenticationService;
import com.borlok.transferservice.security.TokenService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String createAuthenticationToken(AuthenticationRequest authenticationRequest) {
        log.info("Create authentication token");
        return tokenService.createToken(authenticationRequest, passwordEncoder);
    }
}
