package softunicourse.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softunicourse.services.api.TownService;
import softunicourse.services.api.UserService;
import softunicourse.users.entities.Town;
import softunicourse.users.entities.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class CommandRunner implements CommandLineRunner {

    private TownService<Town, Long> townService;

    private UserService<User, Long> userService;

    @Autowired
    public CommandRunner(TownService<Town, Long> townService, UserService<User, Long> userService) {
        this.townService = townService;
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("here");
        //Problem 16:
        //this.userService.getUsersWithDomain("gmail.com")
        //        .forEach(u -> System.out.println(u.getUsername() + " " + u.getEmail()));

        //Problem 17
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       //int width = Integer.parseInt(reader.readLine());
       //int userCount = this.userService.getAllUserWithPicturesBiggerThan(new byte[width]);

       //if (userCount == 0) {
       //    System.out.println("No users have profile picture wider than " + width + " pixels");
       //} else {
       //    System.out.printf("%d %s have profile pictures wider than %d pixels",
       //            userCount,
       //            userCount > 1 ? "users" : "user",
       //            width);
       //}

        //Problem 18
       // String dateStr = reader.readLine();
       // Integer usersDeleted = this.userService.markInactiveUsers(dateStr);
       // if (usersDeleted == 0) {
       //     System.out.println("No users have been deleted");
       // } else {
       //     System.out.printf("%d user%s been deleted\n",
       //             usersDeleted,
       //             usersDeleted == 1 ? " has" : "s have");
//
       //     this.userService.deleteUsersWhichAreMarked();
       // }

        User user = new User();
        user.setFirstName("Gosho");
        user.setLastName("Goshov");
        user.setPassword("AADAS1DSAD"); // valid pass
        //user.setPassword("AADASDSAD");// invalid pass
        user.setEmail("abcdc@abv.bg");
        user.setUsername("petran");
        this.userService.save(user);
    }
}
