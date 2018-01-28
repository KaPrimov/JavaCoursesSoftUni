package org.softuni.main.database.repositories;

import java.util.List;

import org.softuni.main.database.models.User;

public class UserRepository extends BaseRepository {

	private boolean create(String username, String password) {
		this.em.persist(new User(username, password));
		return true;
	}
	
	private User[] findAll() {
		List<User> result = this.em.createNativeQuery("SELECT * FROM  users", User.class).getResultList();
		return result.toArray(new User[result.size()]);
	}
	
}
