package app.serviceLayer.impl;

import app.dataLayer.ProductionBatchDao;
import app.entities.batches.ProductionBatch;
import app.serviceLayer.api.ProductionBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductionBatchServiceImpl implements ProductionBatchService<ProductionBatch, Long> {

    private ProductionBatchDao dao;

    @Autowired
    public ProductionBatchServiceImpl(ProductionBatchDao dao) {
        this.dao = dao;
    }

    @Override
    public ProductionBatch findByID(Long id) {
        return (ProductionBatch) dao.findOne(id);
    }

    @Override
    public void remove(ProductionBatch object) {
        dao.delete(object);
    }

    @Override
    public List<ProductionBatch> findAll(Class<ProductionBatch> serviceClass) {
        return dao.findAll();
    }

    @Override
    public void save(ProductionBatch object) {
        this.dao.save(object);
    }

}
