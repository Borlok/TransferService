package com.borlok.transferservice.rest;

import com.borlok.transferservice.exception.user.UserException;
import com.borlok.transferservice.model.TransferRequest;
import com.borlok.transferservice.model.TransferResponse;
import com.borlok.transferservice.service.TokenService;
import com.borlok.transferservice.service.TransferService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Slf4j
@RestController
@RequestMapping("/transfer")
public class TransferRestControllerV1 {
    private final TransferService transferService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest,
                                      @RequestHeader(name = "Authorization") String token) {
        log.info("Transfer request: {}", transferRequest);
        long userId = Long.parseLong(tokenService.getUserId(token));
        return ResponseEntity.ok(TransferResponse.of(transferService.transfer(userId, transferRequest.getUserId(), transferRequest.getValue())));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserException(UserException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
