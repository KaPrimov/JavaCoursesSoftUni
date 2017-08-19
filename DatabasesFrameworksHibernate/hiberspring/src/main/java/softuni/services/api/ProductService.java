package softuni.services.api;

import softuni.dto.binding.xmlWrappers.AddProductsXmlWrapper;

public interface ProductService {
    void saveProducts(AddProductsXmlWrapper productsWrapper);
}
