package app.gamestore;

import app.gamestore.command.Executable;
import app.gamestore.command.commands.*;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;

public class CommandFactory {

    private UserService userService;
    private GameService gameService;

    public CommandFactory(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    public Executable getCommand(String command, String... params) {
        Executable executable = null;
        switch (command.trim().toLowerCase()) {
            case "register":
                return new RegisterCommand(this.userService, this.gameService);
            case "login":
                return new LoginCommand(this.userService, this.gameService);
            case "logout":
                return new LogoutCommand(this.userService, this.gameService);
            case "addgame":
                return new AddGameCommand(this.userService, this.gameService);
            case "allgames":
                return new AllGamesCommand(this.userService, this.gameService);
            case "editgame":
                return new EditGameCommand(this.userService, this.gameService);
            case "gamedetails":
                return new GameDetailsCommand(this.userService, this.gameService);
            case "ownedgames":
                return new BoughtGamesCommand(this.userService, this.gameService);
        }
        return null;
    }

}
