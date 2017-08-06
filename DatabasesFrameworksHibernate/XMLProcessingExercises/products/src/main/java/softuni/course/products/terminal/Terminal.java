package softuni.course.products.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import softuni.course.products.dtos.binding.CategoryDto;
import softuni.course.products.dtos.binding.UserDto;
import softuni.course.products.dtos.binding.add.CategoryAddDto;
import softuni.course.products.dtos.binding.add.ProductAddDto;
import softuni.course.products.dtos.binding.add.UserAddDto;
import softuni.course.products.io.JsonParser;
import softuni.course.products.services.api.CategoryService;
import softuni.course.products.services.api.ProductService;
import softuni.course.products.services.api.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    public static final String JSON_OUT_PATH = "src\\main\\resources\\out\\";

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final JsonParser jsonParser;

    @Autowired
    public Terminal(CategoryService categoryService, UserService userService, ProductService productService, JsonParser jsonParser) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.jsonParser = jsonParser;
    }

    @Override
    public void run(String... strings) throws Exception {
        //this.importUsers();
        //importCategories();
        importProducts();

        //1st Query
        //List<ProductViewDto> productViewDtoList = this.productService.findAllProductsWithPriceBetween(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        //this.jsonParser.serialize(productViewDtoList, "src\\main\\resources\\out\\products-in-range.json");

        //2nd Query
        //List<UserWithSoldProductView> userWithSoldProductViews = this.userService.findAllUsersWithSoldProducts();
        //this.jsonParser.serialize(userWithSoldProductViews, JSON_OUT_PATH + "users-sold-products.json");

        //3rd Task
        //List<CategoryStatisticView> productsCount = this.categoryService.findAllByProductsCount();
        //this.jsonParser.serialize(productsCount, JSON_OUT_PATH + "findAllByProductsCount.json");

        //4th Task
       // AllUsersWIthSoldProducts allUsersWIthSoldProducts = this.userService.getAllWithSoldProducts();
       // this.jsonParser.serialize(allUsersWIthSoldProducts,JSON_OUT_PATH + "allUsers.json");
       // String deb = "";
    }

    private void importUsers() {
        UserAddDto[] users = this.jsonParser.deserialize(UserAddDto[].class, "/in/users.json" );

        for (UserAddDto user : users) {
            this.userService.save(user);
        }

    }

    private void importCategories() {
        CategoryAddDto[] categoryDtos = this.jsonParser.deserialize(CategoryAddDto[].class, "/in/categories.json");

        for (CategoryAddDto categoryDto : categoryDtos) {
            this.categoryService.save(categoryDto);
        }
    }

    private void importProducts() {
        ProductAddDto[] productDtos = this.jsonParser.deserialize(ProductAddDto[].class, "/in/products.json");
        Random rnd = new Random();
        List<UserDto> userDtos = this.userService.findAllDtos();
        List<CategoryDto> categoryDtos = this.categoryService.findAllDtos();
        int count = 0;
        for (ProductAddDto productDto : productDtos) {
            Set<CategoryDto> categoryDtoSet = new HashSet<>();
            UserDto seller = userDtos.get(rnd.nextInt(userDtos.size()));
            UserDto buyer = userDtos.get(rnd.nextInt(userDtos.size()));

            if (buyer.getId().equals(seller.getId()) || count % 10 == 0) {
                buyer = null;
            }

            for (int i = 0; i < rnd.nextInt(10); i++) {
                categoryDtoSet.add(categoryDtos.get(rnd.nextInt(categoryDtos.size())));
            }
            productDto.setCategories(categoryDtoSet);
            productDto.setBuyer(buyer);
            productDto.setSeller(seller);

            this.productService.save(productDto);
            count++;
        }
    }
}
