package com.borlok.transferservice.service;

import com.borlok.transferservice.model.Email;

import java.util.List;

/**
 * @author Erofeevskiy Yuriy
 */


public interface EmailService {
    List<Email> updateForUser(Long userId, List<String> emails);
}
