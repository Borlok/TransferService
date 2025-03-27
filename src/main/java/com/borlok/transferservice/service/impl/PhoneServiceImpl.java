package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.model.Phone;
import com.borlok.transferservice.repository.PhoneRepository;
import com.borlok.transferservice.service.PhoneService;
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
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    @Override
    @Transactional
    public List<Phone> updateForUser(Long userId, List<String> phones) {
        log.info("Updating phones by userId {} with phones {}", userId, phones);
        Set<String> newPhoneAddresses = new HashSet<>(phones);
        List<Phone> existingPhones = phoneRepository.findAllByUserId(userId);
        Set<String> existingPhoneAddresses = existingPhones.stream().map(Phone::getPhone).collect(Collectors.toSet());
        List<Phone> newPhoneList = new ArrayList<>();
        for(Phone email : existingPhones)
            if (!newPhoneAddresses.contains(email.getPhone()))
                phoneRepository.delete(email);
            else
                newPhoneList.add(email);
        for (String email : newPhoneAddresses)
            if (!existingPhoneAddresses.contains(email)) {
                Phone newPhone = new Phone();
                newPhone.setPhone(email);
                phoneRepository.save(newPhone);
                newPhoneList.add(newPhone);
            }
        return newPhoneList;
    }
}
