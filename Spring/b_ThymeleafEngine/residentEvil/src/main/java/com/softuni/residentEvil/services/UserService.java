
package com.softuni.residentEvil.services;

import com.softuni.residentEvil.entities.User;
import com.softuni.residentEvil.models.binding.RegisterUser;

public interface UserService {
	User findByUsername(String username);
	
	void saveUser(RegisterUser userDTO);
}
