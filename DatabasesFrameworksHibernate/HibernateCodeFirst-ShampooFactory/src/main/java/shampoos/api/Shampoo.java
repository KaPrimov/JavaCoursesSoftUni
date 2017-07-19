package shampoos.api;

import batches.impl.ProductionBatch;
import enums.Size;
import ingredients.api.Ingredient;
import labels.impl.ClassicLabel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public interface Shampoo extends Serializable {
    int getId();

    void setId(int id);

    String getBrand();

    void setBrand(String brand);

    ClassicLabel getLabel();

    void setLabel(ClassicLabel label);

    ProductionBatch getBatch();

    void setBatch(ProductionBatch batch);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Size getSize();

    void setSize(Size size);

    Set<Ingredient> getIngredients();

    void setIngredients(Set<Ingredient> ingredients);
}
