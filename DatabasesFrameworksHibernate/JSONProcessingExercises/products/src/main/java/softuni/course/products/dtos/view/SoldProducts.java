package softuni.course.products.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SoldProducts {
    @Expose
    private Integer count;

    @Expose
    private List<ProductDto> products;

    public SoldProducts(Integer count, List<ProductDto> products) {
        this.count = count;
        this.products = products;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
