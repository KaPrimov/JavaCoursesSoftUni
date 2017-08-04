package softuni.course.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.course.products.dtos.view.UserWithSoldProductsDto;
import softuni.course.products.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User AS u INNER JOIN u.soldProducts AS p " +
            "WHERE u.soldProducts.size > 0 " +
            "AND p.buyer.id IS NOT NULL " +
            "ORDER BY u.lastName, u.lastName")
    List<User> findAllUsersWithSoldProducts();

    @Query("SELECT new softuni.course.products.dtos.view.UserWithSoldProductsDto(s.firstName,s.lastName,s.age) FROM User AS s INNER JOIN s.soldProducts AS sp " +
            "WHERE s.soldProducts.size > 0 " +
            "AND sp.buyer.id IS NOT NULL " +
            "GROUP BY s.id " +
            "ORDER BY s.soldProducts.size DESC, s.lastName ASC")
    List<UserWithSoldProductsDto> findAllWithSoldProductsOrdered();

}
