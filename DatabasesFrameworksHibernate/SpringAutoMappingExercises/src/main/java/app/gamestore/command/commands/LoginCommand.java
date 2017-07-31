package app.gamestore.command.commands;

import app.gamestore.command.BaseCommand;
import app.gamestore.dto.bindingDtos.user.LoggedInUser;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;
import app.gamestore.utils.Session;

public class LoginCommand extends BaseCommand {

    public LoginCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        String userName = params[0];
        String password = params[1];
        if (Session.getLoggedInUser() != null) {
            return "User already logged in";
        }
        LoggedInUser loggedInUser = super.getUserService().login(userName, password);
        if (loggedInUser == null) {
            return "Incorrect email / password";
        }

        Session.setLoggedInUser(loggedInUser);

        return String.format("Successfully logged in %s", loggedInUser.getFullName());
    }
}
