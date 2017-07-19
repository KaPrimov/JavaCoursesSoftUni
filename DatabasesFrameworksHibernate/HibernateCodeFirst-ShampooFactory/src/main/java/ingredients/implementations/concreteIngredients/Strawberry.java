package ingredients.implementations.concreteIngredients;

import ingredients.implementations.BasicIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Strawberry")
public class Strawberry extends BasicIngredient {

    private static final String NAME = "Strawberry";
    private static final double PRICE = 4.84;

    public Strawberry() {
        super(NAME, PRICE);
    }
}
