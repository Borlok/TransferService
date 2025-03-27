package com.borlok.transferservice.rest;

import com.borlok.transferservice.model.AuthenticationRequest;
import com.borlok.transferservice.service.AuthenticationService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationRestControllerV1 {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        log.info("User's authentication: {}", authenticationRequest.username());
        return ResponseEntity.ok(authenticationService.createAuthenticationToken(authenticationRequest));
    }
}
