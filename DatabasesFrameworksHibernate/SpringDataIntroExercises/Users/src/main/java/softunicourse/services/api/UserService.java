package softunicourse.services.api;

import java.util.List;

public interface UserService<User, Long> extends ServiceInterface<User, Long> {

    List<User> getUsersWithDomain(String end);

    Integer getAllUserWithPicturesBiggerThan(byte[] size);

    Integer markInactiveUsers(String date);

    Integer deleteUsersWhichAreMarked();

}
