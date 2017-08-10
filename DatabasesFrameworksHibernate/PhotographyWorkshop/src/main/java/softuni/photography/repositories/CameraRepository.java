package softuni.photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.photography.entities.Camera;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {

}
