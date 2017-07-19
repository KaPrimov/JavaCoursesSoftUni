package shampoos.impl;

import enums.Size;
import ingredients.api.Ingredient;
import ingredients.implementations.concreteIngredients.Mint;
import ingredients.implementations.concreteIngredients.Strawberry;
import labels.impl.ClassicLabel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

public class FiftyShades extends BasicShampoo {

    public FiftyShades() {
        super("Fifty Shades", new ClassicLabel("Fifty Shades", "Tie the aroma"),
                new BigDecimal("6.69"), Size.SMALL,
                new HashSet<Ingredient>(Arrays.asList(new Strawberry(), new Mint())));
    }
}
