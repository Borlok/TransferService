package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.model.Email;
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
    public void updateForUser(List<Email> existingEmails, List<String> emails) {
        Set<String> newEmailAddresses = new HashSet<>(emails);
        Set<String> existingEmailAddresses = existingEmails.stream().map(Email::getEmail).collect(Collectors.toSet());
        for(Email email : existingEmails)
            if (!newEmailAddresses.contains(email.getEmail()))
                emailRepository.delete(email);
        for (String email : newEmailAddresses)
            if (!existingEmailAddresses.contains(email)) {
                Email newEmail = new Email();
                newEmail.setEmail(email);
                emailRepository.save(newEmail);
            }
    }

    @Override
    public Optional<Email> getByEmail(String email) {
        return emailRepository.findByEmail(email);
    }
}
