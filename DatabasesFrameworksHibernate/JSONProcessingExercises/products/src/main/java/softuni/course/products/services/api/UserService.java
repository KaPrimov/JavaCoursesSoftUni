package softuni.course.products.services.api;

import softuni.course.products.dtos.binding.UserDto;
import softuni.course.products.dtos.binding.add.UserAddDto;
import softuni.course.products.dtos.view.AllUsersWIthSoldProducts;
import softuni.course.products.dtos.view.UserWithSoldProductView;

import java.util.List;

public interface UserService {
    void save (UserAddDto user);

    List<UserDto> findAllDtos();

    List<UserWithSoldProductView> findAllUsersWithSoldProducts();

    AllUsersWIthSoldProducts getAllWithSoldProducts();


}
