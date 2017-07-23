package app.entities.batches;

import app.entities.shampoos.BasicShampoo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public interface Batch extends Serializable {

    long getId();

    void setId(long id);

    Date getBatchDate();

    void setBatchDate(Date batchDate);

    Set<BasicShampoo> getShampoos();

    void setShampoos(Set<BasicShampoo> shampoos);
}

