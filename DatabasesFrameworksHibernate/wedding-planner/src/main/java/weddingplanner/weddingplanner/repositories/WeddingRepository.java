package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weddingplanner.weddingplanner.entities.Wedding;

import java.util.List;

@Repository
public interface WeddingRepository extends JpaRepository<Wedding, Long> {
    List<Wedding> findAllByAgency_Id(Long agency_id);
}
