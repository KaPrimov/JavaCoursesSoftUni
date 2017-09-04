package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weddingplanner.weddingplanner.entities.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
}
