package com.borlok.transferservice.service;

import com.borlok.transferservice.model.Email;

import java.util.List;
import java.util.Optional;

/**
 * @author Erofeevskiy Yuriy
 */


public interface EmailService {
    Optional<Email> getByEmail(String username);
    void updateForUser(List<Email> oldEmails, List<String> emails);
}
