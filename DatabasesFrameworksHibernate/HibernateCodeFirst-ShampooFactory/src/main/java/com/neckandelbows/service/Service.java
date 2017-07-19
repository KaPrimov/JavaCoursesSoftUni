package com.neckandelbows.service;

import java.util.List;

/**
 * Created by User on 15.7.2017 г..
 */
public interface Service<E, K> {
    void save(E entity);

    void delete(E entity);

    E findById(Class<E> entityClass, K id);

    List<E> findAll(Class<E> entityClass);

    void dispose();
}
