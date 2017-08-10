package softuni.photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.photography.entities.Photographer;
import softuni.photography.entities.Workshop;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {


}
