package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import weddingplanner.weddingplanner.entities.Wedding;

public interface WeddingRepository extends JpaRepository<Wedding, Long> {
}
