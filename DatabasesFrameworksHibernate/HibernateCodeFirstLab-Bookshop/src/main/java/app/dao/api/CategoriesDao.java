package app.dao.api;

import app.domain.Category;

public interface CategoriesDao extends Dao {

    Category findByName(String categoryName);

}
