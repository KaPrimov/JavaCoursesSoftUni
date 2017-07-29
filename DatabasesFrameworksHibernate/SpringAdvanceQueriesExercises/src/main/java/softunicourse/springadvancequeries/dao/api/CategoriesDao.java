package softunicourse.springadvancequeries.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softunicourse.springadvancequeries.domain.Category;

import java.util.List;

@Repository
public interface CategoriesDao extends JpaRepository<Category, Long> {

    Category findByName(String categoryName);

    @Query("SELECT c.name, sum(b.price * b.copies) FROM Book AS b INNER JOIN b.categories AS c " +
            "GROUP BY c.name " +
            "ORDER BY sum(b.price * b.copies) DESC, c.name")
    List<Object[]> findTotalProfitByCategory();

    @Query("SELECT c FROM Category AS c INNER JOIN c.books AS b " +
            "GROUP BY c " +
            "HAVING count(b) > 35 ORDER BY count(b) DESC ")
    List<Category> findCategoriesWithMoreThan35Books();

}
