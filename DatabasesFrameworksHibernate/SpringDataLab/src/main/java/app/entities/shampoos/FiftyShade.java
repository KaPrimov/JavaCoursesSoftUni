package app.entities.shampoos;

import app.entities.batches.ProductionBatch;
import app.entities.enums.Size;
import app.entities.labels.ClassicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FS")
public class FiftyShade extends BasicShampoo {

    private static final String BRAND = "Fifty Shades";

    private static final BigDecimal PRICE = new BigDecimal("6.69");

    private static final Size SIZE = Size.SMALL;

    public FiftyShade() {
    }

    public FiftyShade(ClassicLabel classicLabel, ProductionBatch productionBatch) {
        super(BRAND, PRICE, SIZE, classicLabel, productionBatch);
    }
}
