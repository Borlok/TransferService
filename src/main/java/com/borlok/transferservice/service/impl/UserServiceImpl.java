package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.exception.user.UserException;
import com.borlok.transferservice.exception.user.UserExceptionMessage;
import com.borlok.transferservice.model.*;
import com.borlok.transferservice.repository.UserRepository;
import com.borlok.transferservice.service.EmailService;
import com.borlok.transferservice.service.PhoneService;
import com.borlok.transferservice.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Erofeevskiy Yuriy
 */


@Data
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PhoneService phoneService;
    @Override
    public Optional<User> getByEmailOrPhoneNumber(String username) {
        log.info("getByEmailOrPhoneNumber: {}", username);
        return Optional.empty();
    }

    @Override
    @Transactional
    public UserDto update(Long userId, UserRequest userRequest) {
        log.info("update: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(UserExceptionMessage.USER_IS_NOT_EXIST));
        List<Email> emails = emailService.updateForUser(user.getId(), userRequest.emails());
        List<Phone> phones = phoneService.updateForUser(user.getId(), userRequest.phones());
        return UserDto.of(user, emails, phones);
    }
}
