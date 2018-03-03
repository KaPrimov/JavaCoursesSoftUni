package repositories;

import entities.User;

public class UserRepository extends BaseRepository {
    public void createUser(User user) {
        this.execute(actionResult -> {
            this.entityManager.persist(user);
        });
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
}
