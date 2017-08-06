package softuni.course.products.dtos.view;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SoldProductView {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String buyerFirstName;
    @Expose
    private String buyerLastName;

    public SoldProductView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }
}
