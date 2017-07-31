package app.gamestore.command.commands;

import app.gamestore.command.BaseCommand;
import app.gamestore.dto.viewDtos.game.GameDetailView;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;

public class GameDetailsCommand extends BaseCommand {

    public GameDetailsCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        GameDetailView gdv = super.getGameService().findByTitle(params[0]);
        if (gdv == null) {
            return "We do not have such game.";
        }
        return gdv.toString();
    }
}
