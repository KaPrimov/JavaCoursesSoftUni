package dao.impl;

import dao.api.Dao;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractJpaDao<E, K> implements Dao<E, K> {

    private final EntityManager em;

    protected AbstractJpaDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(E entity) {
        em.persist(entity);
    }

    @Override
    public void delete(E entity) {
        em.remove(entity);
    }

    @Override
    public E findById(Class<E> entityClass, K id) {
        System.out.println(Long.parseLong((String)id));
        return em.find(entityClass, Long.parseLong((String)id));
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
    public void beginTransaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }

    }

    @Override
    public void commit() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.getTransaction().commit();
    }

    @Override
    public void persist(E entity) {
        this.em.persist(entity);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
