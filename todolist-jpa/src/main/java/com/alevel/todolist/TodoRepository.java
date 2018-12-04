package com.alevel.todolist;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TodoRepository {
    private EntityManager entityManager;

    public TodoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Long save(Todo entity) throws TodoException {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entity.getId();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new TodoException(e);
        }
    }

    public void batchUpdate(Iterable<Todo> batch) throws TodoException {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            for (Todo todo : batch) {
                entityManager.merge(todo);
            }
            transaction.commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new TodoException(e);
        }
    }

    public List<Todo> listAllNotDone() throws TodoException {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Todo> query = criteriaBuilder
                    .createQuery(Todo.class);
            Root<Todo> root = query.from(Todo.class);
            CriteriaQuery<Todo> select = query.select(root)
                    .where(criteriaBuilder.isFalse(root.get("done")));
            return entityManager.createQuery(select).getResultList();
        } catch (Exception e) {
            throw new TodoException(e);
        }
    }

}
