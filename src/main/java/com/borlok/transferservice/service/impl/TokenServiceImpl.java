package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.exception.auth.AuthenticationException;
import com.borlok.transferservice.exception.auth.AuthenticationExceptionMessage;
import com.borlok.transferservice.model.AuthenticationRequest;
import com.borlok.transferservice.model.User;
import com.borlok.transferservice.service.TokenService;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.security.auth.kerberos.EncryptionKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    private final BCryptPasswordEncoder passwordEncoder;
    @Value("${secret}")
    private String secretKey;

    @PostMapping
    public void init() {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void authenticate(User user, AuthenticationRequest authenticationRequest) {
        log.info("Authenticating user {}", user);
        if (!passwordEncoder.matches(authenticationRequest.password(), user.getPassword()))
            throw new AuthenticationException(AuthenticationExceptionMessage.PASSWORD_NOT_MATCHED);
    }

    @Override
    public String createToken(User user) {
        log.info("Creating token for user {}", user);
        return Jwts.builder()
                .claim("user_id", user.getId().toString())
                .signWith(new EncryptionKey(secretKey.getBytes(StandardCharsets.UTF_8),1))
                .compact();
    }
}
