package com.borlok.transferservice.rest;

import com.borlok.transferservice.model.UserRequest;
import com.borlok.transferservice.model.UserResponse;
import com.borlok.transferservice.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Erofeevskiy Yuriy
 */
@Data
@Slf4j
@RestController
@RequestMapping("/users")
public class UserRestControllerV1 {
    private final UserService userService;

    @PutMapping
    public ResponseEntity<?> update(@RequestHeader("principal_user_id") Long userId, UserRequest userRequest) {
        log.info("Updating user");
        return ResponseEntity.ok(UserResponse.of(userService.update(userId, userRequest)));
    }
}
