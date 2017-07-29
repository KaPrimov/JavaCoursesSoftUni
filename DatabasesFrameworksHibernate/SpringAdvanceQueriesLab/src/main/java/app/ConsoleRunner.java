package app;

import app.model.*;
import app.service.api.IngredientService;
import app.service.api.ProductionBatchService;
import app.service.api.ShampoosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 18.7.2017 г..
 */
@Component
public class ConsoleRunner implements CommandLineRunner{

    @Autowired
    private ProductionBatchService<ProductionBatch, Long> productionBatchService;

    @Autowired
    private IngredientService<BasicIngredient, Long> basicIngredientService;

    @Autowired
    private ShampoosService<BasicShampoos, Long> basicShampoosService;

    @Override
    public void run(String... strings) throws Exception {
        //seedData();

//        ProductionBatch pb = productionBatchService.findById(1l);
//        System.out.println(pb);
//
//
//        List<ProductionBatch> batches = productionBatchService.findBatchByName("FirstSeries");
//        System.out.println(batches);
//
//
//        List<BasicShampoos> shampoos = basicShampoosService.shampoosWithIngredient(i1.getName());
//        System.out.println(shampoos);
//
//        List<BasicShampoos> shampoos1 = basicIngredientService.shampoosWithIngredient(i3);
//        System.out.println(shampoos1);
//
//        List<BasicChemicatIngredient> ings = basicIngredientService.findChemicalIngWithFormula("NH4CL");
//        System.out.println(ings);

        //basicShampoosService.solveAllTasks();
        //productionBatchService.solveAll();

        //System.out.println(this.basicIngredientService.findByNameStartingWith("S"));

        //System.out.println(this.basicShampoosService.countAllByPriceLessThan(BigDecimal.valueOf(3.62)));
        System.out.println(this.basicShampoosService.findBasicShampoosByIngredients(1));
    }

    private void seedData() throws ParseException {
        BasicIngredient i1 = new Mint();
        BasicIngredient i2 = new Mint();
        BasicIngredient i3 = new Strawberry();
        BasicIngredient i4 = new AmoniumCloride();
        basicIngredientService.save(i1);
        basicIngredientService.save(i2);
        basicIngredientService.save(i3);
        basicIngredientService.save(i4);
//
//        BasicIngredient bi = basicIngredientService.findById(4l);
//        BasicIngredient b1 = basicIngredientService.findById(3l);
//        BasicIngredient b2 = basicIngredientService.findById(2l);
//        BasicIngredient b3 = basicIngredientService.findById(1l);
//        System.out.println(basicIngredientService.findByNameEndsWith("nt"));
//
        ClassicLabel label = new ClassicLabel("Fresh Shine");
        BasicShampoos shampoo = new FreshNuke();
        Set<BasicIngredient> ingredients = new HashSet<>();
        ingredients.add(i1);
        ingredients.add(i3);
        shampoo.setIngredients(ingredients);
        shampoo.setLabel(label);
        shampoo.setBrand("Brand1");
        shampoo.setSize(3);
        shampoo.setPrice(BigDecimal.valueOf(3.5));
        //em.persist(label);
        ProductionBatch batch = new ProductionBatch();
        batch.setDate(new SimpleDateFormat("YYYY-MM-DD").parse("2010-10-10"));
        batch.setName("FirstSeries");
        shampoo.setBatch(batch);
        this.productionBatchService.save(batch);
        basicShampoosService.save(shampoo);
        ProductionBatch batch1 = new ProductionBatch();
        batch1.setDate(new SimpleDateFormat("YYYY-MM-DD").parse("2013-12-09"));
        batch1.setName("SecondSeries");
        productionBatchService.save(batch1);
        BasicShampoos shampoo1 = new FreshNuke();
        shampoo1.setIngredients(ingredients);
        shampoo1.setBatch(batch);
        shampoo1.setLabel(new ClassicLabel());
        shampoo1.setBrand("Brand2");
        shampoo1.setSize(2);
        shampoo1.setPrice(BigDecimal.valueOf(6.78));
        basicShampoosService.save(shampoo1);
    }
}
