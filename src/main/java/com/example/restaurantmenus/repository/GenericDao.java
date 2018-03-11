package com.example.restaurantmenus.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
    T findOne(final PK id);

    List<T> findAll();

    void create(final T t);

    void update(final PK id, T t);

    void delete(final T t);

    void deleteById(final PK id);
}