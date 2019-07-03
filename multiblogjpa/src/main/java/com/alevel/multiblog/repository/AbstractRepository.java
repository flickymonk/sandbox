package com.alevel.multiblog.repository;

import javax.persistence.EntityManager;

public abstract class AbstractRepository<T, ID> implements Repository<T, ID> {

    final EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }
}
