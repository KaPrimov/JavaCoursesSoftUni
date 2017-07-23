package app.dataLayer;

import app.entities.labels.ClassicLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelsDao extends JpaRepository<ClassicLabel, Long> {
}
