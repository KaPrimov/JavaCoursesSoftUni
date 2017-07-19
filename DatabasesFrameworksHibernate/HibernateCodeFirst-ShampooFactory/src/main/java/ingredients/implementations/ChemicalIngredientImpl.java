package ingredients.implementations;

import ingredients.api.ChemicalIngredient;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ChemicalIngredientImpl extends BasicIngredient implements ChemicalIngredient {

    @Column(name = "chemical_ingredient")
    private String ChemicalFormula;

    public ChemicalIngredientImpl(String name, double price, String chemicalFormula) {
        super(name, price);
        ChemicalFormula = chemicalFormula;
    }

    public ChemicalIngredientImpl() {
    }

    public String getChemicalFormula() {
        return ChemicalFormula;
    }

    public void setChemicalFormula(String chemicalFormula) {
        ChemicalFormula = chemicalFormula;
    }
}
