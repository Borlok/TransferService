package com.borlok.transferservice.rest;

import com.borlok.transferservice.model.UserRequest;
import com.borlok.transferservice.model.UserResponse;
import com.borlok.transferservice.model.UserSearchParameters;
import com.borlok.transferservice.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping
    public ResponseEntity<?> getUsersByParameters(@RequestHeader("principal_user_id") Long userId,
                                                  @RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "email", required = false) String email,
                                                  @RequestParam(value = "phone", required = false) String phone,
                                                  @RequestParam(value = "date_of_birth", required = false) LocalDate dateOfBirth
                                                  ) {
        return ResponseEntity.ok(userService.findByParameters(userId, UserSearchParameters.of(name, email, phone, dateOfBirth)));
    }
}
