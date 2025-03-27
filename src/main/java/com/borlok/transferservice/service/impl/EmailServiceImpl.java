package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.model.Email;
import com.borlok.transferservice.repository.EmailRepository;
import com.borlok.transferservice.service.EmailService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Erofeevskiy Yuriy
 */

@Slf4j
@Data
@Service
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;

    @Override
    @Transactional
    public List<Email> updateForUser(Long userId, List<String> emails) {
        log.info("Updating emails for user {} with emails {}", userId, emails);
        Set<String> newEmailAddresses = new HashSet<>(emails);
        List<Email> existingEmails = emailRepository.findAllByUserId(userId);
        Set<String> existingEmailAddresses = existingEmails.stream().map(Email::getEmail).collect(Collectors.toSet());
        List<Email> newEmailList = new ArrayList<>();
        for(Email email : existingEmails)
            if (!newEmailAddresses.contains(email.getEmail()))
                emailRepository.delete(email);
            else
                newEmailList.add(email);
        for (String email : newEmailAddresses)
            if (!existingEmailAddresses.contains(email)) {
                Email newEmail = new Email();
                newEmail.setEmail(email);
                emailRepository.save(newEmail);
                newEmailList.add(newEmail);
            }
        return newEmailList;
    }
}
