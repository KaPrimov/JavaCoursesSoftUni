package app.gamestore.command.commands;

import app.gamestore.command.BaseCommand;
import app.gamestore.dto.bindingDtos.user.LoggedInUser;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;
import app.gamestore.utils.Session;

public class LogoutCommand extends BaseCommand {
    public LogoutCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        if (Session.getLoggedInUser() == null) {
            return "Cannot log out. No user was logged in.";
        }

        LoggedInUser loggedInUser = Session.getLoggedInUser();
        Session.setLoggedInUser(null);

        return String.format("User %s successfully logged out", loggedInUser.getFullName());
    }
}
