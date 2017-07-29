package app.dao.impl;

import app.dao.api.CategoriesDao;
import app.domain.Category;

import javax.persistence.Query;

public final class CategoriesDaoImpl extends AbstractJpaDao implements CategoriesDao {

    @Override
    public Category findByName(String categoryName) {
        Query query = em.createQuery("FROM Category c WHERE c.name= :name");
        query.setParameter("name", categoryName);
        return (Category) query.getSingleResult();
    }
}
