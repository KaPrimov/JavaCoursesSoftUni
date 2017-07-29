package app.dao.api;

import app.model.BasicIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 18.7.2017 г..
 */

public interface IngredientsDao extends JpaRepository<BasicIngredient, Long>, IngedientsDaoCustom {
    List<BasicIngredient> findByNameEndingWith (String suffix);

    List<BasicIngredient> findAllByPriceIsNullOrderByNameDescPriceDesc();

    List<BasicIngredient> findByNameStartingWith(String name);

    List<BasicIngredient> findAllByNameIn(Collection<String> name);


}
