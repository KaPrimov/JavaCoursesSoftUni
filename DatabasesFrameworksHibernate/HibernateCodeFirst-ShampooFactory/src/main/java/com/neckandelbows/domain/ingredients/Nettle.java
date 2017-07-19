package com.neckandelbows.domain.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "nettle")
@DiscriminatorValue(value = "NT")
public class Nettle extends BasicIngredient {

    private static final String NAME = "Nettle";

    private static final BigDecimal PRICE = new BigDecimal("6.12");

    public Nettle() {
        super(NAME,PRICE);
    }
}
