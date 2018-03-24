package org.softuni.nuggets.services;

import org.softuni.nuggets.entities.User;
import org.softuni.nuggets.models.binding.RegisterBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(RegisterBindingModel bindingModel);

    User findUserByUsername(String username);
}
