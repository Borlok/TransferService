package com.borlok.transferservice.repository.impl;

import com.borlok.transferservice.model.Email;
import com.borlok.transferservice.model.Phone;
import com.borlok.transferservice.model.User;
import com.borlok.transferservice.model.UserSearchParameters;
import com.borlok.transferservice.repository.UserSearchRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Erofeevskiy Yuriy
 */

@Slf4j
public class UserSearchRepositoryImpl implements UserSearchRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAllByParameters(Long userId, UserSearchParameters userSearchParameters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> userQuery = cb.createQuery(User.class);
        Root<User> userRoot = userQuery.from(User.class);
        Join<User, Email> emailJoin = userRoot.join("phones");
        Join<User, Phone> phoneJoin = userRoot.join("emails");

        CriteriaQuery<User> multiselect = userQuery.select(userRoot).multiselect(userRoot.get("id"), phoneJoin.get("email"), emailJoin.get("phone"));
        nameFilter(multiselect, cb, userRoot, userSearchParameters.getName());
        emailFilter(multiselect, cb, emailJoin, userSearchParameters.getEmail());
        phoneFilter(multiselect, cb, phoneJoin, userSearchParameters.getPhone());
        birthFilter(multiselect, cb, userRoot, userSearchParameters.getDateOfBirth());
        return em.createQuery(multiselect).getResultList();
    }

    private void nameFilter(CriteriaQuery<User> multiselect, CriteriaBuilder cb, Root<User> userRoot, String name) {
        if (name == null)
            return;
       multiselect.where(cb.like(cb.lower(userRoot.get("name")), name.toLowerCase() + "%"));
    }

    private void emailFilter(CriteriaQuery<User> multiselect, CriteriaBuilder cb, Join<User, Email> emailJoin, String email) {
        if (email == null)
            return;
        multiselect.where(cb.equal(emailJoin.get("email"), email));
    }

    private void phoneFilter(CriteriaQuery<User> multiselect, CriteriaBuilder cb, Join<User, Phone> phoneJoin, String phone) {
        if (phone == null)
            return;
        multiselect.where(cb.equal(phoneJoin.get("phone"), phone));
    }

    private void birthFilter(CriteriaQuery<User> multiselect, CriteriaBuilder cb, Root<User> userRoot, LocalDate birth) {
        if (birth == null)
            return;
        multiselect.where(cb.greaterThan(userRoot.get("birthDate"), birth));
    }
}
