package app.gamestore.command.commands;

import app.gamestore.command.BaseCommand;
import app.gamestore.dto.bindingDtos.game.AddGame;
import app.gamestore.entities.enums.Role;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;
import app.gamestore.utils.DataValidator;
import app.gamestore.utils.Session;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddGameCommand extends BaseCommand {
    public AddGameCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        if (Session.getLoggedInUser() == null) {
            return "No user logged in";
        }
        if (Session.getLoggedInUser().getRole() != Role.ADMIN) {
            return "Only admins add games!";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            AddGame addGame = new AddGame(params[0],
                    new BigDecimal(params[1]), Double.valueOf(params[2]),
                    params[3], params[4], params[5], sdf.parse(params[6]));
            if (!DataValidator.isValid(addGame)) {
                return DataValidator.getInvalidMessage(addGame);
            }
            super.getGameService().addGame(addGame);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.format("Added %s", params[0]);
    }
}
