package app.gamestore.command.commands;

import app.gamestore.command.BaseCommand;
import app.gamestore.dto.bindingDtos.user.LoggedInUser;
import app.gamestore.dto.viewDtos.game.SingleBoughtGameView;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;
import app.gamestore.utils.Session;

import java.util.List;
import java.util.stream.Collectors;

public class BoughtGamesCommand extends BaseCommand {

    public BoughtGamesCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        LoggedInUser loggedInUser = Session.getLoggedInUser();
        List<SingleBoughtGameView> games = super.getGameService().findBoughtGames(loggedInUser.getId());
        if (games.isEmpty()) {
            return "No games are bough by user " + loggedInUser.getFullName();
        }
        return games
                .stream()
                .map(SingleBoughtGameView::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
