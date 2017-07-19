package ingredients.api;

import java.io.Serializable;

public interface Ingredient extends Serializable {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    double getPrice();

    void setPrice(double price);
}
