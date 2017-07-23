package app.entities.shampoos;

import app.entities.batches.ProductionBatch;
import app.entities.enums.Size;
import app.entities.ingredients.BasicIngredient;
import app.entities.labels.ClassicLabel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicShampoo implements Shampoo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    private String brand;

    @Basic
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Size size;

    @OneToOne(optional = false, cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private ClassicLabel label;

    @ManyToOne(optional = false, cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private ProductionBatch batch;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "shampoos_ingredients",
    joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    protected BasicShampoo() {
        this.setIngredients(new HashSet<>());
    }

    protected BasicShampoo(String brand, BigDecimal price, Size size, ClassicLabel classicLabel, ProductionBatch productionBatch) {
        this();
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.label = classicLabel;
        this.batch = productionBatch;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Size getSize() {
        return this.size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public ClassicLabel getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(ClassicLabel label) {
        this.label = label;
    }

    @Override
    public ProductionBatch getBatch() {
        return this.batch;
    }

    @Override
    public void setBatch(ProductionBatch batch) {
        this.batch = batch;
    }

    @Override
    public Set<BasicIngredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public void setIngredients(HashSet<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "BasicShampoo{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", label=" + label +
                ", batch=" + batch +
                ", ingredients=" + ingredients +
                '}';
    }
}
