package app.dao.impl;

import app.dao.api.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class AbstractJpaDao<E, K> implements Dao<E, K> {

    protected final EntityManager em;

    public AbstractJpaDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookshop");
        this.em = emf.createEntityManager();
    }

    @Override
    public void save(E entity) {
        em.persist(entity);
    }

    @Override
    public void beginTransaction() {
        em.getTransaction().begin();
    }

    @Override
    public void delete(E entity) {
        em.remove(entity);
    }

    @Override
    public E findById(Class<E> entityClass, K id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<E> findAll(Class<E> entityClass) {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " AS e").getResultList();
    }

    @Override
    public void rollback() {
        em.getTransaction().rollback();
    }

    @Override
    public void commit() {
        em.getTransaction().commit();
    }
}
