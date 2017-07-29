package app.serrvice.impl;

import app.dao.api.ProductionBatchDao;
import app.model.ProductionBatch;
import app.service.api.ProductionBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 18.7.2017 г..
 */
@Service
@Transactional
public class ProductionBatchServiceImpl implements ProductionBatchService<ProductionBatch, Long> {

    @Autowired
    private ProductionBatchDao dao;
    @Override
    public List<ProductionBatch> findBatchByName(String name) {
        return ((ProductionBatchDao) dao).findByName(name);
    }
    @Override
    public ProductionBatch findById(Long id) {
        return dao.findOne(id);
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
        dao.save(object);
    }

    @Override
    public void solveAll() {
        //Problem 5
        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        try {
            date1 = dateformat3.parse("10/10/1989");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<ProductionBatch> pbByDateAfter = dao.findByDateAfter(date1);
        System.out.println(pbByDateAfter);

        //Problem 9
        List<ProductionBatch> emptyProdBatches = dao.findByShampoosIsNull();
        System.out.println(emptyProdBatches);
    }
}
