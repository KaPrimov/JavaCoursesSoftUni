package app.service.impl;

import app.dao.api.Dao;
import app.dao.impl.AuthorsDaoImpl;
import app.dao.impl.BookDaoImpl;
import app.dao.impl.CategoriesDaoImpl;
import app.service.api.AuthorService;
import app.service.api.BookService;
import app.service.api.CategoryService;
import app.service.api.Service;
import app.transaction.Command;
import app.transaction.MultiCommand;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E, K> implements Service<E, K> {

    protected Dao<E, K> dao;

    public AbstractService() {
        if (this instanceof BookService) {
            dao = new BookDaoImpl();
        } else if (this instanceof AuthorService) {
            dao = new AuthorsDaoImpl();
        } else if (this instanceof CategoryService) {
            dao = new CategoriesDaoImpl();
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
    public E findById(Class<E> entityClass, K id) {
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

    public static void main(String[] args) {
        List<BigDecimal> list = new ArrayList<>();
        Optional<BigDecimal> optional = list.stream()
                .sorted((a,b) -> b.compareTo(a))
                .findFirst();

    }
}
