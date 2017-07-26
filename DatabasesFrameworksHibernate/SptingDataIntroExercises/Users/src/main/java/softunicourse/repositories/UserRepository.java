package softunicourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softunicourse.users.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> getAllByEmailEndingWith(String end);

    Integer getAllByProfilePictureGreaterThan(byte[] size);

    @Query("UPDATE User AS u SET u.deleted = true WHERE u.lastTimeLoggedIn < :date")
    @Modifying
    Integer markInactiveUsers(@Param("date") String date);

    @Modifying
    Integer deleteUsersByDeletedEquals(Boolean bool);
}
