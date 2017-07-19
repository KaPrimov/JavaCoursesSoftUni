package com.neckandelbows.domain.ingredients;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ammonium_chloride")
@DiscriminatorValue(value = "AM")
public class AmmoniumChloride extends BasicChemicalIngredient{

    private static final BigDecimal PRICE = new BigDecimal("6.12");

    private static final String NAME = "Nettle";

    private static final String CHEMICAL_FORMULA = "NH4Cl";

    public AmmoniumChloride() {
        super(NAME,PRICE,CHEMICAL_FORMULA);
    }
}