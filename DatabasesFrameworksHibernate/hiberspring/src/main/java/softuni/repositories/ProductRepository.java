package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT sum (p.clients) FROM Product AS p " +
            "INNER JOIN p.branch AS b " +
            "INNER JOIN b.town AS t " +
            "GROUP BY t.name " +
            "HAVING t.name = :townName")
    Integer returnClientsInTown(@Param("townName") String townName);

    @Query("SELECT sum (p.clients) FROM Product AS p " +
            "INNER JOIN p.branch AS b " +
            "GROUP BY b.name " +
            "HAVING b.name = :branchName")
    Integer returnClientsInBranch(@Param("branchName") String branchName);
}
