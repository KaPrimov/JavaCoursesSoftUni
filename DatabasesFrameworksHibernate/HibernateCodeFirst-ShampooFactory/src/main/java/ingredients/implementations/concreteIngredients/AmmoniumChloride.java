package ingredients.implementations.concreteIngredients;

import ingredients.implementations.ChemicalIngredientImpl;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Ammonium Chloride")
public class AmmoniumChloride extends ChemicalIngredientImpl {
    private static final String NAME = "Ammonium Chloride";

    private static final double PRICE = 0.59;

    private static final String CHEMICAL_FORMULA = "NH4Cl";

    public AmmoniumChloride() {
        super(NAME, PRICE, CHEMICAL_FORMULA);
    }

}
