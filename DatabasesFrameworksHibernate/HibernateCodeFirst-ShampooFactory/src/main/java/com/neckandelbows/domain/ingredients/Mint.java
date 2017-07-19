package com.neckandelbows.domain.ingredients;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "mint")
@DiscriminatorValue(value = "MN")
public class Mint extends BasicIngredient{

    private static final String NAME = "Mint";

    private static final BigDecimal PRICE = new BigDecimal("3.54");

    public Mint() {
        super(NAME, PRICE);
    }
}
