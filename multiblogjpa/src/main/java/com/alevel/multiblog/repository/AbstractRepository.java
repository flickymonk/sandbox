package com.alevel.multiblog.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

abstract class AbstractRepository<T, ID> implements Repository<T, ID> {

    final EntityManager entityManager;

    AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    List<T> findAll(Class<T> type) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        CriteriaQuery<T> selectAll = cq.select(root);
        TypedQuery<T> query = entityManager.createQuery(selectAll);
        return query.getResultList();
    }

    Optional<T> find(ID id, Class<T> type) {
        T entity = entityManager.find(type, id);
        return Optional.ofNullable(entity);
    }
}
