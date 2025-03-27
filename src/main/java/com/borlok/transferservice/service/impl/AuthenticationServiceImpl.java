package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.model.AuthenticationRequest;
import com.borlok.transferservice.model.User;
import com.borlok.transferservice.exception.user.UserException;
import com.borlok.transferservice.exception.user.UserExceptionMessage;
import com.borlok.transferservice.service.AuthenticationService;
import com.borlok.transferservice.service.TokenService;
import com.borlok.transferservice.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public String createAuthenticationToken(AuthenticationRequest authenticationRequest) {
        log.info("Create authentication token");
        User user = userService.getByEmailOrPhoneNumber(authenticationRequest.username()).orElseThrow(() -> new UserException(UserExceptionMessage.USER_IS_NOT_EXIST));
        log.debug("User is found: {}", user);
        tokenService.authenticate(user, authenticationRequest);
        log.debug("Authentication are pass");
        return tokenService.createToken(user);
    }
}
