package com.alevel.multiblog.repository;

import com.alevel.multiblog.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractRepository<User, Long> {

    public UserRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<User> findAll() {
        return findAll(User.class);
    }

    public Optional<User> findByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        CriteriaQuery<User> selectByEmail = cq.select(root)
                .where(root.get("email").in(email));
        TypedQuery<User> query = entityManager.createQuery(selectByEmail);
        User user = query.getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> find(Long id) {
        return find(id, User.class);
    }
}
