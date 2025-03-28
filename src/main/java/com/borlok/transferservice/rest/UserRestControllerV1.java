package com.borlok.transferservice.rest;

import com.borlok.transferservice.exception.user.UserException;
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

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long userId, @RequestBody UserRequest userRequest) {
        log.info("Updating user with id {}", userId);
        return ResponseEntity.ok(UserResponse.of(userService.update(userId, userRequest)));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getUsersByParameters(@RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "email", required = false) String email,
                                                  @RequestParam(value = "phone", required = false) String phone,
                                                  @RequestParam(value = "date_of_birth", required = false) LocalDate dateOfBirth
                                                  ) {
        return ResponseEntity.ok(UserResponse.of(userService.findByParameters(UserSearchParameters.of(name, email, phone, dateOfBirth))));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserException(UserException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
