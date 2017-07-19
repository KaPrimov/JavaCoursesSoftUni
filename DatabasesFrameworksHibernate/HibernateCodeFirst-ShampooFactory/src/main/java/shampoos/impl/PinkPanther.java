package shampoos.impl;

import enums.Size;
import ingredients.implementations.concreteIngredients.AmmoniumChloride;
import ingredients.implementations.concreteIngredients.Lavender;
import ingredients.implementations.concreteIngredients.Mint;
import labels.impl.ClassicLabel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

public class PinkPanther extends BasicShampoo {
    public PinkPanther() {
        super("Pink Panther", new ClassicLabel("Pink Panther", "Pleasure in pink"),
                new BigDecimal("8.50"), Size.MEDIUM,
                new HashSet<>(Arrays.asList(new Lavender(), new Mint(), new AmmoniumChloride())));
    }
}
