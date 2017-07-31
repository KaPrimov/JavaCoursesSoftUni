package app.gamestore.services.impl;

import app.gamestore.dto.bindingDtos.user.LoggedInUser;
import app.gamestore.dto.bindingDtos.user.RegisterUser;
import app.gamestore.entities.User;
import app.gamestore.entities.enums.Role;
import app.gamestore.utils.ModelParser;
import app.gamestore.repositories.UserRepository;
import app.gamestore.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(RegisterUser user) {
        User concreteUser = ModelParser.getInstance().map(user, User.class);
        concreteUser.setRole(Role.USER);
        if (this.userRepository.findAll().size() == 0) {
            concreteUser.setRole(Role.ADMIN);
        }
        this.userRepository.saveAndFlush(concreteUser);
    }

    @Override
    public LoggedInUser login(String email, String password) {
        User user = this.userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            return null;
        }

        return ModelParser.getInstance().map(user, LoggedInUser.class);
    }
}
