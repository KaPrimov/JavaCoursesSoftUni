package softuni.course.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.course.products.dtos.view.CategoryStatisticView;
import softuni.course.products.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new softuni.course.products.dtos.view.CategoryStatisticView(c.name,c.products.size,AVG(p.price),SUM(p.price)) FROM Category AS c " +
            "INNER JOIN c.products AS p " +
            "GROUP BY c.id " +
            "ORDER BY c.products.size")
    List<CategoryStatisticView> findAllByProductsCount();
}
