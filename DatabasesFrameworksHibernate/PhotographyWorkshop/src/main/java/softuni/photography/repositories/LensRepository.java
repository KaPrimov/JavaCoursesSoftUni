package softuni.photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.photography.entities.Lens;

import java.util.List;

@Repository
public interface LensRepository extends JpaRepository<Lens, Long> {

    List<Lens> findAllByIdIn(Iterable<Long> ids);

}
