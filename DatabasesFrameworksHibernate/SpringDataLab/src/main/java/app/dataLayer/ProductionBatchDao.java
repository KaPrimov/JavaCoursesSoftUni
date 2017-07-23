package app.dataLayer;

import app.entities.batches.ProductionBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionBatchDao extends JpaRepository<ProductionBatch, Long> {


}
