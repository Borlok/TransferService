package com.borlok.transferservice.repository;

import com.borlok.transferservice.model.Email;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Erofeevskiy Yuriy
 */

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    @EntityGraph("email_user")
    Optional<Email> findByEmail(String email);
}
