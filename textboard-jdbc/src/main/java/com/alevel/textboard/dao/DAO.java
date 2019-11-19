package com.alevel.textboard.dao;

import com.alevel.textboard.model.Persisted;

import java.util.List;
import java.util.Optional;

public interface DAO<ID, T> {

    List<Persisted<ID, T>> findAll();

    Optional<Persisted<ID, T>> findById(ID id);

    ID save(T entity);

    void update(ID id, T entity);

    void delete(ID id);

}
