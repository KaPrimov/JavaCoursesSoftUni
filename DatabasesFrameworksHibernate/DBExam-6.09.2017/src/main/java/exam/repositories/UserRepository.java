package exam.repositories;

import exam.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    @Query("SELECT u.username, u.followers.size FROM User AS u " +
            "ORDER BY u.id")
    List<Object[]> findAllPopularUsers();
}
