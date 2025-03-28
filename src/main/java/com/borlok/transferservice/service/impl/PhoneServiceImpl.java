package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.model.Phone;
import com.borlok.transferservice.model.User;
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
    public void updateForUser(User user, List<String> phones) {
        log.info("Updating existing phones");
        Set<String> newPhoneAddresses = new HashSet<>(phones);
        List<Phone> existingPhone = new ArrayList<>(user.getPhones());
        Set<String> existingPhoneAddresses = existingPhone.stream().map(Phone::getPhone).collect(Collectors.toSet());
        for (String phone : newPhoneAddresses)
            if (!existingPhoneAddresses.contains(phone)) {
                log.info("Adding new phone {}", phone);
                Phone newPhone = new Phone();
                newPhone.setUser(user);
                newPhone.setPhone(phone);
                user.getPhones().add(phoneRepository.save(newPhone));
            }
        for(Phone phone : existingPhone) {
            if (!newPhoneAddresses.contains(phone.getPhone())) {
                log.info("Removing existing phone {}", phone.getPhone());
                phoneRepository.delete(phone);
                user.getPhones().remove(phone);
            }
        }
    }

    @Override
    public Optional<Phone> getByPhone(String phone) {
        log.info("Get phone {}", phone);
        return phoneRepository.findByPhone(phone);
    }
}
