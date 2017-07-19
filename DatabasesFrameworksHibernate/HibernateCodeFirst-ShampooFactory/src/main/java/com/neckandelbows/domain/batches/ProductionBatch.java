package com.neckandelbows.domain.batches;


import com.neckandelbows.domain.shampoos.BasicShampoo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "batches")
public class ProductionBatch implements Batch{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "batch_date")
    private Date batchDate;

    @OneToMany(mappedBy = "batch", targetEntity = BasicShampoo.class, cascade = CascadeType.ALL)
    private Set<BasicShampoo> shampoos;

    public ProductionBatch() {
        this.setShampoos(new HashSet<>());
    }

    public ProductionBatch(Date batchDate) {
        this();
        this.setBatchDate(batchDate);
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Date getBatchDate() {
        return this.batchDate;
    }

    @Override
    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    @Override
    public Set<BasicShampoo> getShampoos() {
        return this.shampoos;
    }

    @Override
    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }

    @Override
    public String toString() {
        return "ProductionBatch{" +
                "id=" + id +
                ", batchDate=" + batchDate +
                '}';
    }
}
