package repositories;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository extends BaseRepository {
    public void createUser(User user) {
        this.execute(actionResult -> {
            this.entityManager.persist(user);
        });
    }

    public List<User> findAll() {
        List<User> result = new ArrayList<User>();
        this.execute(actionResult -> {
            result.addAll(this.entityManager.createNativeQuery(
                    "SELECT * FROM users", User.class
            ).getResultList());
        });
        return result;
    }

    public User findByUsername(String username) {
        User result = (User) this.execute(actionResult -> {
            actionResult.setActionResult(
                    this.entityManager
                    .createNativeQuery(
                            "SELECT * FROM users AS u WHERE u.username = \'" + username + "\'", User.class)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null));
        }).getActionResult();

        return result;
    }

    public User findById(String id) {
        User result = (User) this.execute(actionResult -> {
            actionResult.setActionResult(
                    this.entityManager
                            .createNativeQuery(
                                    "SELECT * FROM users AS u WHERE u.id = \'" + id + "\'", User.class)
                            .getResultList()
                            .stream()
                            .findFirst()
                            .orElse(null));
        }).getActionResult();

        return result;
    }

    public void addFriend(User user, User friend) {

        user.getFriends().add(friend);
        try {
            this.initEntityManager();
            this.initTransaction();
            this.entityManager.merge(user);
            this.commitTransaction();
            this.dispose();
        } catch (Exception e) {
            if(this.transaction != null) {
                this.transaction.rollback();
            }

            e.printStackTrace();
        }

    }

    public void unfriend (User user, String friendName) {
        try {
            this.initEntityManager();
            this.initTransaction();
            user.getFriends().removeIf(u -> u.getUsername().equalsIgnoreCase(friendName));
            this.entityManager.merge(user);
            this.commitTransaction();
            this.dispose();
        } catch (Exception e) {
            if(this.transaction != null) {
                this.transaction.rollback();
            }

            e.printStackTrace();
        }

    }
}
