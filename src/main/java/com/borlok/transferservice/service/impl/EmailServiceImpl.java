package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.model.Email;
import com.borlok.transferservice.model.User;
import com.borlok.transferservice.repository.EmailRepository;
import com.borlok.transferservice.service.EmailService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    public void updateForUser(User user, List<String> emails) {
        log.info("Updating existing emails");
        Set<String> newEmailAddresses = new HashSet<>(emails);
        List<Email> existingEmails = new ArrayList<>(user.getEmails());
        Set<String> existingEmailAddresses = existingEmails.stream().map(Email::getEmail).collect(Collectors.toSet());
        for (String email : newEmailAddresses)
            if (!existingEmailAddresses.contains(email)) {
                log.info("Adding email {}", email);
                Email newEmail = new Email();
                newEmail.setUser(user);
                newEmail.setEmail(email);
                user.getEmails().add(emailRepository.save(newEmail));
            }
        for(Email email : existingEmails)
            if (!newEmailAddresses.contains(email.getEmail())) {
                log.info("Removing existing email {}", email);
                emailRepository.delete(email);
                user.getEmails().remove(email);
            }
    }

    @Override
    public Optional<Email> getByEmail(String email) {
        log.info("Retrieving email with email {}", email);
        return emailRepository.findByEmail(email);
    }
}
