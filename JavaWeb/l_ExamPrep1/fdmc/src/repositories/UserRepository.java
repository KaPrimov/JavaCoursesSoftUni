package repositories;

import entities.User;

public class UserRepository extends BaseRepository {
    public void createUser(User user) {
        this.execute(actionResult -> {
            this.em.persist(user);
        });
    }

    public User findById(String userId) {
        User user = (User) this.execute(actionResult -> {
            Object queryResult =
                    this.em.createNativeQuery("SELECT * FROM users AS u WHERE u.id = \'" + userId + "\'", User.class)
                            .getResultList()
                            .stream()
                            .findFirst()
                            .orElse(null);

            actionResult.setResult(queryResult);
        }).getResult();

        return user;
    }

    public User findByUsername(String username) {
        User user = (User) this.execute(actionResult -> {
            Object queryResult =
                    this.em.createNativeQuery("SELECT * FROM users AS u WHERE u.username = \'" + username + "\'", User.class)
                            .getResultList()
                            .stream()
                            .findFirst()
                            .orElse(null);

            actionResult.setResult(queryResult);
        }).getResult();

        return user;
    }
}
