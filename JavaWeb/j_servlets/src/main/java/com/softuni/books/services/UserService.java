package com.softuni.books.services;

import com.softuni.books.models.bindingModels.LoginModel;

public interface UserService {
	void createUser(LoginModel loginModel);

    LoginModel findByUsernameAndPassword(String username, String password);
}
