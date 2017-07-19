package com.neckandelbows.domain.ingredients;

import com.neckandelbows.domain.shampoos.BasicShampoo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public interface Ingredient extends Serializable {

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Set<BasicShampoo> getShampoos();

    void setShampoos(Set<BasicShampoo> shampoos);
}