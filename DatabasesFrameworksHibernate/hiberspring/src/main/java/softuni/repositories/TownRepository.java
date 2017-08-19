package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    Town findByName(String name);
}
