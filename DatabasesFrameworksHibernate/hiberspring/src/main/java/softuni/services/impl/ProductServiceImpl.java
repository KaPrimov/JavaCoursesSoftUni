package softuni.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.dto.binding.AddProductXmlDto;
import softuni.dto.binding.xmlWrappers.AddProductsXmlWrapper;
import softuni.entities.Product;
import softuni.io.MessageWriter;
import softuni.repositories.BranchRepository;
import softuni.repositories.ProductRepository;
import softuni.services.api.ProductService;
import softuni.utils.CustomValidator;
import softuni.utils.DTOConvertUtil;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public void saveProducts(AddProductsXmlWrapper productsWrapper) {
        for (AddProductXmlDto addProductXmlDto : productsWrapper.getProductXmlDtos()) {
            Product product = DTOConvertUtil.convert(addProductXmlDto, Product.class);
            product.setBranch(this.branchRepository.findByName(addProductXmlDto.getBranch()));
            if (CustomValidator.isValid(product)) {
                MessageWriter.getInstance().printSuccess(Product.class, product.getName());
                this.productRepository.save(product);
            } else {
                MessageWriter.getInstance().printError();
            }
        }
    }
}
