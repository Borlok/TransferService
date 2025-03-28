package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.exception.user.UserException;
import com.borlok.transferservice.exception.user.UserExceptionMessage;
import com.borlok.transferservice.model.*;
import com.borlok.transferservice.repository.UserRepository;
import com.borlok.transferservice.repository.impl.UserSpecification;
import com.borlok.transferservice.service.EmailService;
import com.borlok.transferservice.service.PhoneService;
import com.borlok.transferservice.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public User getByEmailOrPhoneNumber(String username) {
        log.info("getByEmailOrPhoneNumber: {}", username);
        return emailService.getByEmail(username).map(Email::getUser)
                .or(() -> phoneService.getByPhone(username).map(Phone::getUser))
                .orElseThrow(() -> new UserException(UserExceptionMessage.USER_IS_NOT_EXIST));
    }

    @Override
    @Transactional
    public User update(Long userId, UserRequest userRequest) {
        log.info("update: {}", userRequest);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(UserExceptionMessage.USER_IS_NOT_EXIST));
        emailService.updateForUser(user, userRequest.getEmails());
        phoneService.updateForUser(user, userRequest.getPhones());
        return userRepository.save(user);
    }

    @Override
    public List<User> findByParameters(UserSearchParameters userSearchParameters) {
        log.info("findByParameters: {}", userSearchParameters);
        Specification<User> spec = Specification.where(null);
        spec = UserSpecification.nameFilter(spec, userSearchParameters.getName())
                .and(UserSpecification.emailFilter(spec, userSearchParameters.getEmail()))
                .and(UserSpecification.phoneFilter(spec, userSearchParameters.getPhone()))
                .and(UserSpecification.birthFilter(spec, userSearchParameters.getDateOfBirth()));
        return userRepository.findAll(spec);
    }
}
