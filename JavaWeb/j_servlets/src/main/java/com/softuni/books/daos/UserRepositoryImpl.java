package com.softuni.books.daos;

import java.util.HashMap;
import java.util.Map;

import com.softuni.books.entities.User;

public class UserRepositoryImpl implements UserRepository {
	
	private static UserRepositoryImpl userRepository;
	private Map<String, User> users;
	
	private UserRepositoryImpl() {
		this.users = new HashMap<>();
	}
	
	public static UserRepository getInstance() {
		if (userRepository == null) {
			userRepository = new UserRepositoryImpl();
		}
		return userRepository;
	}
	
	@Override
	public void createUser(User user) {
		this.users.putIfAbsent(user.getUsername(), user);

	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		if (this.users.containsKey(username)) {			
			return this.users.get(username).equals(password) ? this.users.get(username) : null;
		}
		return null;
	}

}
