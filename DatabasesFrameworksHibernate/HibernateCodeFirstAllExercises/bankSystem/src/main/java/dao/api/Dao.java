package dao.api;

import service.transactions.TransactionSupport;

import javax.persistence.EntityManager;
import java.util.List;

public interface Dao<E, K> extends TransactionSupport<E> {

    void save(E entity);

    void delete(E entity);

    E findById(Class<E> entityClass, K id);

    List<E> findAll(Class<E> entityClass);

    void persist(E entity);

    EntityManager getEntityManager();
}
