package app.dao.impl;

import app.dao.api.BookDao;
import app.domain.Author;
import app.domain.Book;

import javax.persistence.Query;

public final class BookDaoImpl extends AbstractJpaDao implements BookDao {
    @Override
    public Book findByTitle(String title) {
        Query query = em.createQuery("FROM Book b WHERE b.title= :title");
        query.setParameter("title", title);
        return (Book) query.getSingleResult();
    }

    @Override
    public Book findByAuthor(Author author) {
        Query query = em.createQuery("FROM Book b WHERE b.author= :author");
        query.setParameter("author", author);
        return (Book) query.getSingleResult();
    }
}
