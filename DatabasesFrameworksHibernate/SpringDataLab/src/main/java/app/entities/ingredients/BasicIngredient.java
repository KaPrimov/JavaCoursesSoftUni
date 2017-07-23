package app.entities.ingredients;


import app.entities.shampoos.BasicShampoo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "igredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ingredient_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicIngredient implements Ingredient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.ALL)
    private Set<BasicShampoo> shampoos;

    protected BasicIngredient() {
        this.setShampoos(new HashSet<>());
    }

    protected BasicIngredient(String name, BigDecimal price) {
        this();
        this.setName(name);
        this.setPrice(price);
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
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public Set<BasicShampoo> getShampoos() {
        return this.shampoos;
    }

    @Override
    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos=shampoos;
    }

    @Override
    public String toString() {
        return "BasicIngredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}