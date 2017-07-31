package app.gamestore.services.api;

import app.gamestore.dto.bindingDtos.user.LoggedInUser;
import app.gamestore.dto.bindingDtos.user.RegisterUser;

public interface UserService {
    void save(RegisterUser user);

    LoggedInUser login(String email, String password);
}
