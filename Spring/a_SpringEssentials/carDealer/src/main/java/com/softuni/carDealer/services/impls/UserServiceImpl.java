package com.softuni.carDealer.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.carDealer.dtos.binding.add.RegisterUser;
import com.softuni.carDealer.entities.User;
import com.softuni.carDealer.repositories.UserRepository;
import com.softuni.carDealer.services.apis.UserService;
import com.softuni.carDealer.utils.ModelParser;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(RegisterUser userDTO) {
		this.userRepository.save(ModelParser.getInstance().map(userDTO, User.class));
	}

}
