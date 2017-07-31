package app.gamestore.services.api;

import app.gamestore.dto.bindingDtos.user.BoughtGamesUser;
import app.gamestore.dto.bindingDtos.user.LoggedInUser;
import app.gamestore.dto.bindingDtos.user.RegisterUser;
import app.gamestore.dto.bindingDtos.user.ShoppingCartUser;

public interface UserService<T> {
    void save(RegisterUser user);

    LoggedInUser login(String email, String password);

    ShoppingCartUser findById(Long id);

    void persistShoppingCartUser(ShoppingCartUser scu);

    BoughtGamesUser findBoughtGameUser(Long id);

    void saveBoughGameUser(BoughtGamesUser bgu);
}
