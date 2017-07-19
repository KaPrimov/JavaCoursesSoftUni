package com.neckandelbows.serviceImpl;
import com.neckandelbows.service.Service;
import com.neckandelbows.transactions.Command;
import com.neckandelbows.transactions.MultiCommand;
import com.neckandelbows.transactions.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by User on 15.7.2017 г..
 */
public class ServiceImpl<E, K> implements Service<E, K>, Transactional<E> {

    private static final String PERSISTENCE_UNIT_NAME = "shampooCompany";

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    private EntityManager em = emf.createEntityManager();;

    @Override
    @SuppressWarnings("unchecked")
    public void save(E entity) {
        runInTransaction((Command) () -> {
            em.merge(entity);
            return entity;
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(E entity) {
        runInTransaction((Command) () -> {
            em.remove(entity);
            return entity;
        });
    }

    @Override
    public E findById(Class<E> entityClass, K id) {
        return runInTransaction(() -> em.find(entityClass, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> findAll(Class<E> entityClass) {
        return runInTransaction(() -> em.createQuery("FROM " + entityClass.getName()).getResultList());
    }

    @Override
    public E runInTransaction(Command<E> command) {
        try {
            em.getTransaction().begin();
            E result = command.execute();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            em.getTransaction().rollback();
            //log here
            throw e;
        }
    }

    @Override
    public List<E> runInTransaction(MultiCommand<E> command) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<E> result = command.execute();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            em.getTransaction().rollback();
            //log here
            throw e;
        }
    }

    @Override
    public void dispose() {
        em.close();
    }
}
