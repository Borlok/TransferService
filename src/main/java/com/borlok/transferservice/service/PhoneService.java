package com.borlok.transferservice.service;

import com.borlok.transferservice.model.Phone;

import java.util.List;
import java.util.Optional;

/**
 * @author Erofeevskiy Yuriy
 */


public interface PhoneService {
    Optional<Phone> getByPhone(String phone);
    void updateForUser(List<Phone> existingPhones, List<String> phones);
}
