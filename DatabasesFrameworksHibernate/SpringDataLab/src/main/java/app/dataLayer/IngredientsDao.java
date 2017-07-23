package app.dataLayer;

import app.entities.ingredients.BasicIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsDao extends JpaRepository<BasicIngredient, Long> {
    BasicIngredient findByName(String name);
}
