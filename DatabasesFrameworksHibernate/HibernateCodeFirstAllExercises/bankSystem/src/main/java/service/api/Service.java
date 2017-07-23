package service.api;

import service.transactions.TransactionExecutor;

import java.util.List;

public interface Service<E, K> extends TransactionExecutor<E> {
    void save(E entity);

    void delete(E entity);

    E findByAccountNumber(Class<E> entityClass, K accountNumber);

    List<E> findAll(Class<E> entityClass);

    void begin();

    void commit();
}
