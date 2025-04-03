package com.borlok.transferservice.repository;

import com.borlok.transferservice.model.Phone;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Erofeevskiy Yuriy
 */

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    @EntityGraph("phone_user")
    Optional<Phone> findByPhone(String phone);
}
