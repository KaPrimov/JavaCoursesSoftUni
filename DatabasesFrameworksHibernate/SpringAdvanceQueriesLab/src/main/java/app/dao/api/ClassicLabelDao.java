package app.dao.api;

import app.model.ClassicLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 24.7.2017 г..
 */
@Repository
public interface ClassicLabelDao extends JpaRepository<ClassicLabel, Long> {
}
