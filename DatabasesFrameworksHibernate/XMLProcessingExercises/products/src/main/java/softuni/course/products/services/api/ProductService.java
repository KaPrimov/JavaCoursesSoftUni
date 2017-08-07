package softuni.course.products.services.api;

import org.springframework.stereotype.Component;
import softuni.course.products.dtos.binding.add.ProductAddDto;
import softuni.course.products.dtos.view.ProductViewDto;
import softuni.course.products.dtos.view.SoldProductView;
import softuni.course.products.dtos.view.SoldProducts;
import softuni.course.products.dtos.view.xmlViews.ProductsXmlViewDto;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface ProductService {
    void save(ProductAddDto productDto);

    List<ProductViewDto> findAllProductsWithPriceBetween(BigDecimal from, BigDecimal to);

    List<SoldProductView> getAllBySellerLastNameAndBuyerNotNull(String name);

    SoldProducts getAllSoldProductsBySellerName(String lastName);

    ProductsXmlViewDto findAllProductsFromXmlWithPriceBetween(BigDecimal from, BigDecimal to);
}
