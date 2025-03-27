package com.borlok.transferservice.repository;

import com.borlok.transferservice.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Erofeevskiy Yuriy
 */

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query(value = "select * from email_data where user_id = ?1", nativeQuery = true)
    List<Email> findAllByUserId(Long userId);
}
