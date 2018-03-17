package com.softuni.residentEvil.services;

import com.softuni.residentEvil.entities.User;
import com.softuni.residentEvil.models.binding.RegisterUser;
import com.softuni.residentEvil.models.view.ViewUser;
import com.softuni.residentEvil.repositories.RoleRepository;
import com.softuni.residentEvil.repositories.UserRepository;
import com.softuni.residentEvil.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired	
	public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

	@Override
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(RegisterUser userDTO) {
		User user = ModelParser.getInstance().map(userDTO, User.class);
		user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		user.setAuthorities(Set.of(roleRepository.findByRole("ROLE_USER")));
		this.userRepository.save(user);
	}

	@Override
	public List<ViewUser> findAllUsers() {
		List<User> allUsers = this.userRepository.findAll();
		List<ViewUser> userDTOs = new LinkedList<>();
		for (User user : allUsers) {
			ViewUser viewUser = new ViewUser();
			viewUser.setUsername(user.getUsername());
			viewUser.setAuthorities(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
		}
		return null;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }
}
