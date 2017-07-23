package app.entities.shampoos;


import app.entities.batches.ProductionBatch;
import app.entities.enums.Size;
import app.entities.labels.ClassicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "PP")
public class PinkPanther extends BasicShampoo {

    private static final String BRAND = "Pink Panter";

    private static final BigDecimal PRICE = new BigDecimal("8.50");

    private static final Size SIZE = Size.MEDIUM;

    public PinkPanther() {
    }

    public PinkPanther(ClassicLabel classicLabel, ProductionBatch productionBatch) {
        super(BRAND, PRICE, SIZE, classicLabel, productionBatch);
    }
}
