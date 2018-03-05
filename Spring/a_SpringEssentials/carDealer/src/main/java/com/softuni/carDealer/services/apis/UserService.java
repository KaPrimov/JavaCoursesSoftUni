
package com.softuni.carDealer.services.apis;

import com.softuni.carDealer.dtos.binding.add.RegisterUser;
import com.softuni.carDealer.entities.User;

public interface UserService {
	User findByUsername(String username);
	
	void saveUser(RegisterUser userDTO);
}
