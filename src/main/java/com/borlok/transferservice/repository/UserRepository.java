package com.borlok.transferservice.repository;

import com.borlok.transferservice.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Erofeevskiy Yuriy
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    @EntityGraph("User_emails_phones")
    @NotNull
    Optional<User> findById(@NotNull Long userId);
    @EntityGraph("User_emails_phones")
    @NotNull
    Page<User> findAll(@Nullable Specification<User> spec, @NotNull Pageable pageable);
}
