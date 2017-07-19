package ingredients.implementations.concreteIngredients;

import ingredients.implementations.BasicIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Mint")
public class Mint extends BasicIngredient {

    private static final String NAME = "Mint";
    private static final double PRICE = 3.54;

    public Mint() {
        super(NAME, PRICE);
    }
}
