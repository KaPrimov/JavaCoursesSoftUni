package softuni.course.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.course.products.dtos.view.ProductDto;
import softuni.course.products.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product AS p WHERE p.price BETWEEN :from AND :to " +
            "ORDER BY p.price")
    List<Product> findAllProductsWithPriceBetween(@Param("from")BigDecimal from,
                                                  @Param("to") BigDecimal to);

    List<Product> findAllBySellerLastNameAndBuyerNotNull(String seller_lastName);

    @Query("SELECT new  softuni.course.products.dtos.view.ProductDto(p.name,p.price) FROM Product AS p " +
            "WHERE p.seller.lastName = :lastName")
    List<ProductDto> findAllBySeller(@Param("lastName") String lastName);
}
