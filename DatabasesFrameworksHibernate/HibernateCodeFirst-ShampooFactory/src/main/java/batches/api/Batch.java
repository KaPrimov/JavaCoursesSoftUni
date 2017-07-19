package batches.api;

import shampoos.api.Shampoo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public interface Batch extends Serializable {
    long getId();

    void setId(long id);

    LocalDate getBatchDate();

    void setBatchDate(LocalDate batchDate);

    Set<Shampoo> getShampoos();

    void setShampoos(Set<Shampoo> shampoos);
}
