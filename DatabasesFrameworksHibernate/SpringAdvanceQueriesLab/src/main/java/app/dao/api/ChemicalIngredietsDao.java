package app.dao.api;

import app.model.BasicChemicatIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 24.7.2017 г..
 */
@Repository
public interface ChemicalIngredietsDao extends JpaRepository<BasicChemicatIngredient, Long> {
    List<BasicChemicatIngredient> findByChemicalFormula(String chemicalFormula);
}
