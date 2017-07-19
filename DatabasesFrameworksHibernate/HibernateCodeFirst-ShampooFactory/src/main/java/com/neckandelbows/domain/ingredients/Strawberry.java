package com.neckandelbows.domain.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "strawberry")
@DiscriminatorValue(value = "ST")
public class Strawberry extends BasicIngredient{

    private static final String NAME = "Strawberry";

    private static final BigDecimal PRICE = new BigDecimal("4.85");

    public Strawberry() {
        super(NAME, PRICE);
    }
}
