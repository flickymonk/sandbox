package com.alevel.multiblog.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    List<T> findAll();

    T save(T entity);

    Optional<T> find(ID id);

}
