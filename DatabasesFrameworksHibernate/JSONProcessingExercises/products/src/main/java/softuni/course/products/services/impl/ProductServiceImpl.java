package softuni.course.products.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.course.products.dtos.binding.add.ProductAddDto;
import softuni.course.products.dtos.view.ProductDto;
import softuni.course.products.dtos.view.ProductViewDto;
import softuni.course.products.dtos.view.SoldProductView;
import softuni.course.products.dtos.view.SoldProducts;
import softuni.course.products.entities.Product;
import softuni.course.products.repositories.ProductRepository;
import softuni.course.products.services.api.ProductService;
import softuni.course.products.utils.ModelParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl() {
    }

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(ProductAddDto productDto) {
        Product product = ModelParser.getInstance().map(productDto, Product.class);

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductViewDto> findAllProductsWithPriceBetween(BigDecimal from, BigDecimal to) {
        List<Product> products = this.productRepository.findAllProductsWithPriceBetween(from, to);
        List<ProductViewDto> productViewDtoList = new ArrayList<>();


        for (Product product : products) {
            ProductViewDto productViewDto = new ProductViewDto();
            productViewDto.setName(product.getName());
            productViewDto.setPrice(product.getPrice());
            productViewDto.setSeller(String.format("%s %s", product.getSeller().getFirstName(), product.getSeller().getLastName()));
            productViewDtoList.add(productViewDto);
        }

        return productViewDtoList;

    }

    @Override
    public List<SoldProductView> getAllBySellerLastNameAndBuyerNotNull(String name) {
        List<Product> products = this.productRepository.findAllBySellerLastNameAndBuyerNotNull(name);
        List<SoldProductView> soldProductViews = new ArrayList<>();
        for (Product product : products) {
            soldProductViews.add(ModelParser.getInstance().map(product, SoldProductView.class));
        }
        return soldProductViews;
    }

    @Override
    public SoldProducts getAllSoldProductsBySellerName(String lastName) {
        List<ProductDto> products = this.productRepository.findAllBySeller(lastName);
        return new SoldProducts(products.size(),products);
    }
}
