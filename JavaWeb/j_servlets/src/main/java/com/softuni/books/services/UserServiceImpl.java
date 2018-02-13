package com.softuni.books.services;

import com.softuni.books.daos.UserRepositoryImpl;
import com.softuni.books.entities.User;
import com.softuni.books.models.bindingModels.LoginModel;
import com.softuni.books.utils.DTOConvertUtil;

public class UserServiceImpl implements UserService {

	@Override
	public void createUser(LoginModel loginModel) {
		UserRepositoryImpl.getInstance().createUser(DTOConvertUtil.convert(loginModel, User.class));
	}

	@Override
	public LoginModel findByUsernameAndPassword(String username, String password) {
		return DTOConvertUtil.convert(UserRepositoryImpl.getInstance().findByUsernameAndPassword(username, password), LoginModel.class);
	}

}
