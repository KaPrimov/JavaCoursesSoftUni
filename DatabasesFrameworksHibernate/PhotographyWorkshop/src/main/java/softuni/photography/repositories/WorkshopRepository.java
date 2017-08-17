package softuni.photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.photography.entities.Workshop;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
//    @Query("SELECT w FROM Workshop AS w " +
//            "GROUP BY w.location " +
//            "HAVING count (w.participants) > 4")
//    List<Workshop> workshopsByLocation();

    @Query("SELECT DISTINCT w.location FROM Workshop AS w")
    List<String> returnAllLocations();

    @Query("SELECT w FROM Workshop AS w WHERE w.location = :location AND w.participants.size > 0")
    List<Workshop> findAllWorkshopAndPaticipantsByLocation(@Param("location") String location);
}
