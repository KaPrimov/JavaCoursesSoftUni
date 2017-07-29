package app.dataLayer;

import app.entities.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShampoosDao extends JpaRepository<BasicShampoo, Long> {

    @Query("SELECT b FROM BasicShampoo b JOIN b.ingredients i WHERE i.name = :ingredientName")
    List<BasicShampoo> shampoosWithIngredient(@Param("ingredientName") String ingredientName);

    List<BasicShampoo> findAllByBrand(String brand);
}
