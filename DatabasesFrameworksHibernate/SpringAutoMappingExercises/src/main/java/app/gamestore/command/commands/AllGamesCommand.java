package app.gamestore.command.commands;

import app.gamestore.command.BaseCommand;
import app.gamestore.dto.viewDtos.game.GameModelView;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class AllGamesCommand extends BaseCommand {

    public AllGamesCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        List<GameModelView> gameViews = super.getGameService().getAll();

        return gameViews
                .stream()
                .map(GameModelView::toString)
                .collect(Collectors.joining("\n"));
    }
}
