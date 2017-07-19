package shampoos.impl;

import batches.impl.ProductionBatch;
import enums.Size;
import ingredients.api.Ingredient;
import labels.impl.ClassicLabel;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Set;

public abstract class BasicShampoo implements shampoos.api.Shampoo {

    private int id;

    private String brand;

    private ClassicLabel label;

    private ProductionBatch batch;

    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    private Size size;

    private Set<Ingredient> ingredients;

    public BasicShampoo(String brand, ClassicLabel label, BigDecimal price, Size size, Set<Ingredient> ingredients) {
        this.brand = brand;
        this.label = label;
        this.price = price;
        this.size = size;
        this.ingredients = ingredients;
    }

    public BasicShampoo() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public ClassicLabel getLabel() {
        return label;
    }

    @Override
    public void setLabel(ClassicLabel label) {
        this.label = label;
    }

    @Override
    public ProductionBatch getBatch() {
        return batch;
    }

    @Override
    public void setBatch(ProductionBatch batch) {
        this.batch = batch;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
