package softuni.course.products.services.api;

import softuni.course.products.dtos.binding.UserDto;
import softuni.course.products.dtos.binding.add.UserAddDto;
import softuni.course.products.dtos.view.AllUsersWIthSoldProducts;
import softuni.course.products.dtos.view.UserWithSoldProductView;
import softuni.course.products.dtos.view.xmlViews.UsersWithSoldProductsXmlWrapper;

import java.util.List;

public interface UserService {
    void save (UserAddDto user);

    List<UserDto> findAllDtos();

    List<UserWithSoldProductView> findAllUsersWithSoldProducts();

    AllUsersWIthSoldProducts getAllWithSoldProducts();

    UsersWithSoldProductsXmlWrapper getAllWithSoldProductsXml();
}
