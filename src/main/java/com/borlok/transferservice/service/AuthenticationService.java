package com.borlok.transferservice.service;

import com.borlok.transferservice.model.AuthenticationRequest;

/**
 * @author Erofeevskiy Yuriy
 */

public interface AuthenticationService {
    String createAuthenticationToken(AuthenticationRequest authenticationRequest);
}
