package app.service.api;

import app.transaction.TransactionExecutor;

import java.util.List;

public interface Service<E, K> extends TransactionExecutor<E> {

    void save(E entity);

    void delete(E entity);

    E findById(Class<E> entityClass, K id);

    List<E> findAll(Class<E> entityClass);

}
