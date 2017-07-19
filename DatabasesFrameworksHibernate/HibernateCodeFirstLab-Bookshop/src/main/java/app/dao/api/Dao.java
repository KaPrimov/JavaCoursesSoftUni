package app.dao.api;

import app.transaction.TransactionSupport;

import java.util.List;

public interface Dao<E, K> extends TransactionSupport<E> {

    void save(E entity);

    void delete(E entity);

    E findById(Class<E> entityClass, K id);

    List<E> findAll(Class<E> entityClass);

}
