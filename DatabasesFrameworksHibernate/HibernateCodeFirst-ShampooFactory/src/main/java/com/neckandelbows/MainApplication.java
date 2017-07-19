package com.neckandelbows;

import com.neckandelbows.domain.batches.ProductionBatch;
import com.neckandelbows.domain.ingredients.AmmoniumChloride;
import com.neckandelbows.domain.ingredients.BasicIngredient;
import com.neckandelbows.domain.ingredients.Mint;
import com.neckandelbows.domain.ingredients.Nettle;
import com.neckandelbows.domain.labels.ClassicLabel;
import com.neckandelbows.domain.shampoos.BasicShampoo;
import com.neckandelbows.domain.shampoos.FiftyShade;
import com.neckandelbows.domain.shampoos.FreshNuke;
import com.neckandelbows.service.*;
import com.neckandelbows.serviceImpl.*;

import java.util.Date;
import java.util.List;

public class MainApplication {
    private BasicIngredientService basicIngredientService = new BasicIngredientServiceImpl();

    public static void main(String[] args) {

        MainApplication app = new MainApplication();
        app.runDemo();
    }

    private void runDemo() {
        Service service = new ServiceImpl();

        try {
            BasicIngredient am = new AmmoniumChloride();
            BasicIngredient mint = new Mint();
            BasicIngredient nettle = new Nettle();

            ProductionBatch productionBatch = new ProductionBatch(new Date());

            ClassicLabel classicLabelOne = new ClassicLabel("Aweseom Tittle", "Subtitle");

            ClassicLabel classicLabelTwo = new ClassicLabel("Aweseom 2", "Subtitle2");

            BasicShampoo basicShampooOne = new FreshNuke(classicLabelOne, productionBatch);
            basicShampooOne.getIngredients().add(am);
            basicShampooOne.getIngredients().add(nettle);

            BasicShampoo basicShampooTwo = new FiftyShade(classicLabelTwo, productionBatch);
            basicShampooTwo.getIngredients().add(am);
            basicShampooTwo.getIngredients().add(mint);

            service.save(basicShampooOne);
            service.save(basicShampooTwo);

            List<BasicShampoo> shampoos = service.findAll(BasicShampoo.class);
            System.out.println(shampoos);
        } finally {
            service.dispose();
        }
    }
}
