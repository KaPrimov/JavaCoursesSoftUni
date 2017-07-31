package app.gamestore.command.commands;

import app.gamestore.command.BaseCommand;
import app.gamestore.dto.bindingDtos.user.BoughtGamesUser;
import app.gamestore.dto.bindingDtos.user.LoggedInUser;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;
import app.gamestore.utils.Session;

public class BuyGamesCommand extends BaseCommand {

    public BuyGamesCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {

        LoggedInUser loggedInUser = Session.getLoggedInUser();

        if (loggedInUser == null) {
            return "No logged in user";
        }

        BoughtGamesUser boughtGamesUser = this.getUserService().findBoughtGameUser(loggedInUser.getId());

        boughtGamesUser.buyAll();

        this.getUserService().saveBoughGameUser(boughtGamesUser);

        return boughtGamesUser.toString();
    }
}
