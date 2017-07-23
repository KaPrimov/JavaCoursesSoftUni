package service.impl;

import dao.api.Dao;
import dao.impl.CheckingAccountDaoImpl;
import dao.impl.SavingsAccountDaoImpl;
import service.api.Service;
import service.transactions.Command;
import service.transactions.MultiCommand;
import service.transactions.VoidCommand;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractService<E, K> implements Service<E, K> {

    protected Dao<E, K> dao;

    protected AbstractService(EntityManager em) {
        if (this instanceof CheckingAccountServiceImpl) {
            dao = new CheckingAccountDaoImpl(em);
        } else if (this instanceof SavingsAccountServiceImpl) {
            dao = new SavingsAccountDaoImpl(em);
        }
    }

    @Override
    public void save(E entity) {
        this.dao.save(entity);
    }

    @Override
    public void delete(E entity) {
        this.dao.delete(entity);
    }

    @Override
    public E findByAccountNumber(Class<E> entityClass, K id) {
        return runInTransaction(() -> dao.findById(entityClass, id));
    }

    @Override
    public List<E> findAll(Class<E> entityClass) {
        return runInTransaction(() -> dao.findAll(entityClass));
    }

    @Override
    public E runInTransaction(Command<E> command) {
        try {
            dao.beginTransaction();
            E result = command.execute();
            dao.commit();
            return result;
        } catch (Exception e) {
            dao.rollback();
            throw e;
        }
    }

    @Override
    public List<E> runInTransaction(MultiCommand<E> commands) {
        try {
            dao.beginTransaction();
            List<E> result = commands.execute();
            dao.commit();
            return result;
        } catch (Exception e) {
            dao.rollback();
            throw e;
        }
    }

    @Override
    public void runInTransaction(VoidCommand command) {
        try {
            dao.beginTransaction();
            command.execute();
            dao.commit();
        } catch (Exception e) {
            dao.rollback();
            throw e;
        }
    }

    @Override
    public void begin() {
        this.dao.beginTransaction();
    }

    @Override
    public void commit() {
        this.dao.commit();
    }
}
