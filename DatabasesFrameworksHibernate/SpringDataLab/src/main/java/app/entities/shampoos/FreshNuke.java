package app.entities.shampoos;

import app.entities.batches.ProductionBatch;
import app.entities.enums.Size;
import app.entities.labels.ClassicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FN")
public class FreshNuke extends BasicShampoo {

    private static final String BRAND = "Fresh Nuke";

    private static final BigDecimal PRICE = new BigDecimal("9.33");

    private static final Size SIZE = Size.LARGE;

    public FreshNuke() {
    }

    public FreshNuke(ClassicLabel classicLabel, ProductionBatch productionBatch) {
        super(BRAND, PRICE, SIZE, classicLabel, productionBatch);
    }
}
