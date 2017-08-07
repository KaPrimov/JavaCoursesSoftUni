package softuni.course.products.dtos.view;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryStatisticView {
    @Expose
    @XmlAttribute(name = "name")
    private String category;
    @Expose
    @XmlElement(name = "products-count")
    private int productsCount;
    @Expose
    @XmlElement(name = "average-price")
    private Double averagePrice;
    @Expose
    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryStatisticView(String category, int productsCount, Double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
