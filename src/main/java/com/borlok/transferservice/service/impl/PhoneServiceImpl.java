package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.model.Phone;
import com.borlok.transferservice.repository.PhoneRepository;
import com.borlok.transferservice.service.PhoneService;
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
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;

    @Override
    @Transactional
    public void updateForUser(List<Phone> existingPhones, List<String> phones) {
        Set<String> newPhoneAddresses = new HashSet<>(phones);
        Set<String> existingPhoneAddresses = existingPhones.stream().map(Phone::getPhone).collect(Collectors.toSet());
        for(Phone phone : existingPhones)
            if (!newPhoneAddresses.contains(phone.getPhone()))
                phoneRepository.delete(phone);
        for (String email : newPhoneAddresses)
            if (!existingPhoneAddresses.contains(email)) {
                Phone newPhone = new Phone();
                newPhone.setPhone(email);
                phoneRepository.save(newPhone);
            }
    }

    @Override
    public Optional<Phone> getByPhone(String phone) {
        return phoneRepository.findByPhone(phone);
    }
}
