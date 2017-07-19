package batches.impl;

import batches.api.Batch;

import java.time.LocalDate;
import java.util.Set;

public class ProductionBatch implements Batch {

    private long id;

    private LocalDate batchDate;

    private Set<Shampoo> shampoos;

    public ProductionBatch() {
    }

    public ProductionBatch(LocalDate batchDate, Set<Shampoo> shampoos) {
        this.batchDate = batchDate;
        this.shampoos = shampoos;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public LocalDate getBatchDate() {
        return batchDate;
    }

    @Override
    public void setBatchDate(LocalDate batchDate) {
        this.batchDate = batchDate;
    }

    @Override
    public Set<Shampoo> getShampoos() {
        return shampoos;
    }

    @Override
    public void setShampoos(Set<Shampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
