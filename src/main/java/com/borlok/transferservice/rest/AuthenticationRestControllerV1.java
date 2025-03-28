package com.borlok.transferservice.rest;

import com.borlok.transferservice.model.AuthenticationRequest;
import com.borlok.transferservice.service.AuthenticationService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
        log.info("User's authentication: {}", authenticationRequest.getUsername());
        return ResponseEntity.ok(authenticationService.createAuthenticationToken(authenticationRequest));
    }

    @GetMapping("/check")
    public ResponseEntity<?> check() {
        return ResponseEntity.ok("Hello user with id " + SecurityContextHolder.getContext().getAuthentication().getName() + "!!!");
    }
}
