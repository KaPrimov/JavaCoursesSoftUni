package com.softuni.books.daos;

import com.softuni.books.entities.User;

public interface UserRepository {

    void createUser(User user);

    User findByUsernameAndPassword(String username, String password);
}
