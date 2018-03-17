
package com.softuni.residentEvil.services;

import com.softuni.residentEvil.entities.User;
import com.softuni.residentEvil.models.binding.RegisterUser;
import com.softuni.residentEvil.models.view.ViewUser;

import java.util.List;

public interface UserService {
	User findByUsername(String username);
	
	void saveUser(RegisterUser userDTO);

	List<ViewUser> findAllUsers();
}
