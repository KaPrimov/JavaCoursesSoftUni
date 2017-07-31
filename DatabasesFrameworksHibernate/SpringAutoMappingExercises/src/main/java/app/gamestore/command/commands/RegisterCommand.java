package app.gamestore.command.commands;

import app.gamestore.command.BaseCommand;
import app.gamestore.dto.bindingDtos.user.RegisterUser;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;
import app.gamestore.utils.DataValidator;

public class RegisterCommand extends BaseCommand {

    public RegisterCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {

        RegisterUser registerUser = new RegisterUser(params[0], params[1], params[2], params[3]);

        if (!DataValidator.isValid(registerUser)) {
            return DataValidator.getInvalidMessage(registerUser);
        }
        try {
            super.getUserService().save(registerUser);
        } catch (Exception ise) {
            return ise.getMessage();
        }
        return String.format("%s was registered", params[0]);
    }
}
