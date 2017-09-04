package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weddingplanner.weddingplanner.entities.Present;

@Repository
public interface PresentRepository extends JpaRepository<Present, Long> {
//    @Query("SELECT count(p) FROM Invitation AS i " +
//            "INNER JOIN i.present AS p " +
//            "INNER JOIN i.wedding AS w " +
//            "GROUP BY i,w " +
//            "HAVING p.present_type = 'Gift' AND " +
//            "w.id=:wedding_id")
//    Long findAllPresentsForTheWedding(@Param("wedding_id") Long id);
}
