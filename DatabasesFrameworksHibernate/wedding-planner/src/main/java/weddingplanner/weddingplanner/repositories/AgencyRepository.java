package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weddingplanner.weddingplanner.entities.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Agency findFirstByName(String name);
}
