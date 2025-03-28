package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.exception.auth.AuthenticationException;
import com.borlok.transferservice.exception.auth.AuthenticationExceptionMessage;
import com.borlok.transferservice.model.AuthenticationRequest;
import com.borlok.transferservice.model.User;
import com.borlok.transferservice.service.TokenService;
import com.borlok.transferservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    private final UserService userService;
    @Value("${secret}")
    private String secretKey;

    @PostMapping
    public void init() {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String createToken(AuthenticationRequest authData, BCryptPasswordEncoder passwordEncoder) {
        log.info("Creating token for user {}", authData);
        User user = userService.getByEmailOrPhoneNumber(authData.getUsername());
        if (!passwordEncoder.matches(authData.getPassword(), user.getPassword()))
            throw new AuthenticationException(AuthenticationExceptionMessage.PASSWORD_NOT_MATCHED);

        return Jwts.builder()
                .subject(authData.getUsername())
                .claim("principal_user_id", user.getId().toString())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    @Override
    public Authentication authenticate(String token) {
        JwtParser build = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build();
        Claims payload = build.parseSignedClaims(token).getPayload();

        String username = payload.getSubject();
        String userId = payload.get("principal_user_id", String.class);

        User user = userService.getByEmailOrPhoneNumber(username);
        if (user == null){
            log.error(AuthenticationExceptionMessage.JWT_TOKEN_IS_INVALID.name());
            SecurityContextHolder.clearContext();
            throw new AuthenticationException(AuthenticationExceptionMessage.JWT_TOKEN_IS_INVALID);
        }
        return new UsernamePasswordAuthenticationToken(userId, "", new ArrayList<>());
    }
}
