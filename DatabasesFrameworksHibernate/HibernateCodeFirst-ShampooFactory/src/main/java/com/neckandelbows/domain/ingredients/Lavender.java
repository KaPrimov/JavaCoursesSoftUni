package com.neckandelbows.domain.ingredients;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "lavender")
@DiscriminatorValue(value = "LV")
public class Lavender extends BasicIngredient{

    private static final String NAME = "Lavender";

    private static final BigDecimal PRICE = new BigDecimal("2");

    public Lavender() {
        super(NAME,PRICE);
    }
}
