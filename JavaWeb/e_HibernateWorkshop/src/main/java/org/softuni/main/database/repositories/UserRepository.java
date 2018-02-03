package org.softuni.main.database.repositories;

import java.util.List;

import org.softuni.main.database.models.User;

public class UserRepository extends BaseRepository {
    private boolean create(String username, String password) {
        this.entityManager.persist(new User(username, password));
        return true;
    }

    private User[] findAll() {
        List<User> resultList = this.entityManager
                .createNativeQuery("SELECT * FROM users", User.class)
                .getResultList();

        return resultList.toArray(new User[resultList.size()]);
    }
    
    private User findByUsername(String username) {
        User resultingObject = (User) this.entityManager
                .createNativeQuery("SELECT * FROM users as u WHERE u.username = \'" + username + "\'", User.class)
                .getSingleResult();

        return resultingObject;
    }
    
    private User findById(String id) {
        User resultingObject = (User) this.entityManager
                .createNativeQuery("SELECT * FROM users as u WHERE u.id = \'" + id + "\'", User.class)
                .getSingleResult();

        return resultingObject;
    }
    
    private void addFriend(String username, String friendName) {
    	User user = this.findByUsername(username);
    	User friend = this.findByUsername(friendName);
    	user.addFriend(friend);
    	
    	this.entityManager.merge(user);
    }
}
