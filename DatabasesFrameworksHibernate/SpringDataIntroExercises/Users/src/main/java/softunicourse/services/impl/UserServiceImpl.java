package softunicourse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softunicourse.repositories.UserRepository;
import softunicourse.services.api.UserService;
import softunicourse.users.entities.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService<User, Long> {

    private UserRepository dao;

    @Autowired
    public UserServiceImpl(UserRepository dao) {
        this.dao = dao;
    }

    @Override
    public User findByID(Long id) {
        return this.dao.findOne(id);
    }

    @Override
    public void remove(User object) {
        this.dao.delete(object);
    }

    @Override
    public List<User> findAll() {
        return this.dao.findAll();
    }

    @Override
    public void save(User object) {
        this.dao.save(object);
    }

    @Override
    public List<User> getUsersWithDomain(String end) {
        return this.dao.getAllByEmailEndingWith(end);
    }

    @Override
    public Integer getAllUserWithPicturesBiggerThan(byte[] size) {
        return this.dao.getAllByProfilePictureGreaterThan(size);
    }

    @Override
    public Integer markInactiveUsers(String date) {
        return this.markInactiveUsers(date);
    }

    @Override
    public Integer deleteUsersWhichAreMarked() {
        return this.dao.deleteUsersByDeletedEquals(true);
    }
}
