package app.gamestore.command.commands;

import app.gamestore.command.BaseCommand;
import app.gamestore.dto.bindingDtos.game.EditGame;
import app.gamestore.dto.bindingDtos.user.LoggedInUser;
import app.gamestore.entities.enums.Role;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;
import app.gamestore.utils.DataValidator;
import app.gamestore.utils.Session;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EditGameCommand extends BaseCommand {

    private final Map<Class, Class> mapper = new LinkedHashMap<Class, Class>() {{
        put(int.class, Integer.class);
        put(double.class, Double.class);
        put(float.class, Float.class);
        put(long.class, Long.class);
        put(boolean.class, Boolean.class);
    }};

    public EditGameCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {

        LoggedInUser loggedInUser = Session.getLoggedInUser();

        if (loggedInUser == null) {
            return "Not logged in";
        }

        if (loggedInUser.getRole() != Role.ADMIN) {
            return "Only admins can edit games";
        }

        Long id = Long.parseLong(params[0]);

        EditGame editGame = this.getGameService().findById(id, EditGame.class);
        if (editGame == null) {
            return "Invalid game id";
        }

        for (int i = 1; i < params.length; i++) {
            String[] paramArgs = params[i].split("=");
            String fieldName = paramArgs[0];
            String value = paramArgs[1];

            try {
                Field field = editGame.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                fieldType = fieldType.isPrimitive() ?
                        this.mapper.get(fieldType) :
                        fieldType;

                Constructor<?> fieldConstructor = fieldType.getConstructor(String.class);
                field.set(editGame, fieldConstructor.newInstance(value));
            } catch (NoSuchFieldException e) {
                return e.getMessage();
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        if (!DataValidator.isValid(editGame)) {
            return DataValidator.getInvalidMessage(editGame);
        }

        super.getGameService().updateGame(editGame);
        return String.format("Edited %s", editGame.getTitle());
    }
}
