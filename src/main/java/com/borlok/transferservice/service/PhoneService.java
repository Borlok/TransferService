package com.borlok.transferservice.service;

import com.borlok.transferservice.model.Phone;

import java.util.List;

/**
 * @author Erofeevskiy Yuriy
 */


public interface PhoneService {
    List<Phone> updateForUser(Long userId, List<String> phones);
}
