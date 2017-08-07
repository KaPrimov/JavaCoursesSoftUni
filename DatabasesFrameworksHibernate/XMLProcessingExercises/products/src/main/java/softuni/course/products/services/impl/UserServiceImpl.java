package softuni.course.products.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.course.products.dtos.binding.UserDto;
import softuni.course.products.dtos.binding.add.UserAddDto;
import softuni.course.products.dtos.view.*;
import softuni.course.products.dtos.view.xmlViews.UsersWithSoldProductsXmlWrapper;
import softuni.course.products.entities.User;
import softuni.course.products.repositories.UserRepository;
import softuni.course.products.services.api.ProductService;
import softuni.course.products.services.api.UserService;
import softuni.course.products.utils.ModelParser;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ProductService productService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public void save(UserAddDto userDto) {
        User user = ModelParser.getInstance().map(userDto, User.class);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserDto> findAllDtos() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(ModelParser.getInstance().map(user, UserDto.class));
        }
        return userDtos;
    }

    @Override
    public List<UserWithSoldProductView> findAllUsersWithSoldProducts() {
        List<User> allUsersWithSoldProducts = this.userRepository.findAllUsersWithSoldProducts();
        List<UserWithSoldProductView> userWithSoldProductViews = new ArrayList<>();

        for (User allUsersWithSoldProduct : allUsersWithSoldProducts) {
            UserWithSoldProductView userWithSoldProductView = ModelParser.getInstance().map(allUsersWithSoldProduct, UserWithSoldProductView.class);
            List<SoldProductView> soldProductViews = this.productService.getAllBySellerLastNameAndBuyerNotNull(userWithSoldProductView.getLastName());
            userWithSoldProductView.setSoldProducts(soldProductViews);
            userWithSoldProductViews.add(userWithSoldProductView);
        }
        return userWithSoldProductViews;
    }

    @Override
    public AllUsersWIthSoldProducts getAllWithSoldProducts() {
        List<UserWithSoldProductsDto> userWithSoldProductsDtos = this.userRepository.findAllWithSoldProductsOrdered();
        for (UserWithSoldProductsDto userWithSoldProductsDto : userWithSoldProductsDtos) {
            SoldProducts soldProducts = this.productService.getAllSoldProductsBySellerName(userWithSoldProductsDto.getLastName());
            userWithSoldProductsDto.setSoldProducts(soldProducts);
        }
        AllUsersWIthSoldProducts allUsersWIthSoldProducts = new AllUsersWIthSoldProducts(userWithSoldProductsDtos.size(),userWithSoldProductsDtos);
        return allUsersWIthSoldProducts ;
    }

    @Override
    public UsersWithSoldProductsXmlWrapper getAllWithSoldProductsXml() {
        List<UserWithSoldProductView> allUsersWithSoldProducts = this.findAllUsersWithSoldProducts();
        UsersWithSoldProductsXmlWrapper usersWithSoldProductsXmlWrapper = new UsersWithSoldProductsXmlWrapper();
        usersWithSoldProductsXmlWrapper.setUserWithSoldProductViews(allUsersWithSoldProducts);
        return usersWithSoldProductsXmlWrapper;
    }
}
