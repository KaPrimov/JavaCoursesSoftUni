package com.softuni.residentEvil.services;

import com.softuni.residentEvil.entities.User;
import com.softuni.residentEvil.entities.enums.RoleEnum;
import com.softuni.residentEvil.models.binding.RegisterUser;
import com.softuni.residentEvil.repositories.RoleRepository;
import com.softuni.residentEvil.repositories.UserRepository;
import com.softuni.residentEvil.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	private RoleRepository roleRepository;
	private UserRepository userRepository;
	
	@Autowired	
	public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	@Override
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(RegisterUser userDTO) {
		User user = ModelParser.getInstance().map(userDTO, User.class);
		user.setRoles(Set.of(roleRepository.findByRole(RoleEnum.USER)));
		this.userRepository.save(user);
	}

}
