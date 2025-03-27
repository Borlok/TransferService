package com.borlok.transferservice.repository;

import com.borlok.transferservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Erofeevskiy Yuriy
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
