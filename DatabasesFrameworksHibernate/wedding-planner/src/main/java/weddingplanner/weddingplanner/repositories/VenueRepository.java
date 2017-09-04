package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import weddingplanner.weddingplanner.entities.Venue;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    @Query(value = "SELECT v.name, v.capacity, count(vw.wedding_id)\n" +
            "  FROM venues AS v\n" +
            "    INNER JOIN venues_weddings AS vw\n" +
            "    ON v.id = vw.venue_id\n" +
            "WHERE v.town = 'Sofia'\n" +
            "GROUP BY v.id\n" +
            "HAVING count(vw.wedding_id) >= 3;", nativeQuery = true)
    List<Object[]> weddingsInSofia();
}
