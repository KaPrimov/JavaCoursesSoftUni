package app.console;

import app.entities.batches.ProductionBatch;
import app.entities.ingredients.BasicIngredient;
import app.entities.ingredients.Mint;
import app.entities.ingredients.Strawberry;
import app.entities.labels.ClassicLabel;
import app.entities.shampoos.BasicShampoo;
import app.entities.shampoos.FiftyShade;
import app.entities.shampoos.PinkPanther;
import app.serviceLayer.api.IngredientService;
import app.serviceLayer.api.LabelService;
import app.serviceLayer.api.ProductionBatchService;
import app.serviceLayer.api.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private ProductionBatchService<ProductionBatch, Long> productionBatchService;

    private IngredientService<BasicIngredient, Long> ingredientService;

    private ShampooService<BasicShampoo, Long> shampooService;

    private LabelService<ClassicLabel, Long> labelService;


    @Autowired
    public ConsoleRunner(ProductionBatchService<ProductionBatch, Long> productionBatchService, IngredientService<BasicIngredient, Long> ingredientService, ShampooService<BasicShampoo, Long> shampooService, LabelService<ClassicLabel, Long> labelService) {
        this.productionBatchService = productionBatchService;
        this.ingredientService = ingredientService;
        this.shampooService = shampooService;
        this.labelService = labelService;
    }
    @Override
    public void run(String... strings) throws Exception {
        BasicIngredient i1 = new Mint();
        i1.setName("Mint");
        BasicIngredient i2 = new Strawberry();
        i2.setName("Berry");

        ProductionBatch productionBatch = new ProductionBatch();
        ClassicLabel classicLabel = new ClassicLabel();

        this.labelService.save(classicLabel);
        this.productionBatchService.save(productionBatch);

        BasicShampoo shampoo = new FiftyShade();
        shampoo.setIngredients(new HashSet<>(Arrays.asList(i1, i2)));
        shampoo.setLabel(classicLabel);
        shampoo.setBatch(productionBatch);

        BasicShampoo shampoo2 = new PinkPanther();
        shampoo2.setIngredients(new HashSet<>(Collections.singletonList(i2)));
        shampoo2.setBatch(productionBatch);
        shampoo2.setLabel(classicLabel);

        this.ingredientService.save(i1);
        this.ingredientService.save(i2);
        this.shampooService.save(shampoo);
        this.shampooService.save(shampoo2);
    }
}
