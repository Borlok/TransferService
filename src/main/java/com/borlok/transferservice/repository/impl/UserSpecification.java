package com.borlok.transferservice.repository.impl;

import com.borlok.transferservice.model.Email;
import com.borlok.transferservice.model.Phone;
import com.borlok.transferservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.time.LocalDate;

/**
 * @author Erofeevskiy Yuriy
 */

@Slf4j
public class UserSpecification {
    public static Specification<User> nameFilter(Specification<User> spec, String name) {
        log.info("Find by name: {}", name);
        if (name == null)
            return spec;
        return spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), name.toLowerCase() + "%"));
    }

    public static Specification<User> emailFilter(Specification<User> spec, String email) {
        log.info("Find by email: {}", email);
        if (email == null)
            return spec;
        return spec.and((root, query, criteriaBuilder) -> {
            Join<Email, User> userJoin = root.join("emails");
            return criteriaBuilder.equal(userJoin.get("email"), email);
        });
    }
    public static Specification<User> phoneFilter(Specification<User> spec, String phone) {
        log.info("Find by phone: {}", phone);
        if (phone == null)
            return spec;
        return spec.and((root, query, criteriaBuilder) -> {
            Join<Phone, User> userJoin = root.join("phones");
            return criteriaBuilder.equal(userJoin.get("phone"), phone);
        });
    }

    public static Specification<User> birthFilter(Specification<User> spec, LocalDate dateOfBirth) {
        log.info("Find by birth: {}", dateOfBirth);
        if (dateOfBirth == null)
            return spec;
        return spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("dateOfBirth"), dateOfBirth));
    }
}
