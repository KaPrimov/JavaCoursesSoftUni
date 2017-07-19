package ingredients.implementations.concreteIngredients;

import ingredients.implementations.BasicIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Lavender")
public class Lavender extends BasicIngredient {

    private static final String NAME = "Lavender";
    private static final int PRICE = 2;

    public Lavender() {
        super(NAME, PRICE);
    }
}
