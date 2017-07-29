package app.dao.impl;

import app.dao.api.AuthorsDao;
import app.domain.Author;

import javax.persistence.Query;

public final class AuthorsDaoImpl extends AbstractJpaDao implements AuthorsDao {
    @Override
    public Author findAuthorByFirstName(String firstName) {
        Query query = em.createQuery("FROM Author a WHERE a.firstName = :name");
        query.setParameter("name", firstName);
        return (Author) query.getSingleResult();
    }

    @Override
    public Author findAuthorByLastName(String lastName) {
        Query query = em.createQuery("FROM Author a WHERE a.lastName = :name");
        query.setParameter("name", lastName);
        return (Author) query.getSingleResult();
    }
}
