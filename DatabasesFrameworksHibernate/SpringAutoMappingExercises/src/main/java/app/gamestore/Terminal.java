package app.gamestore;

import app.gamestore.command.Executable;
import app.gamestore.factories.CommandFactory;
import app.gamestore.services.api.GameService;
import app.gamestore.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

@Component
public class Terminal implements CommandLineRunner {

    private final UserService userService;

    private final GameService gameService;

    @Autowired
    public Terminal(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... strings) throws Exception {
        CommandFactory commandFactory = new CommandFactory(this.userService, this.gameService);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = reader.readLine();

            if ("END".equals(line)) {
                break;
            }

            String[] tokens = line.split("\\|");
            String command = tokens[0];

            String[] newParams = new String[tokens.length - 1];

            if (tokens.length > 1) {
                newParams = Arrays.copyOfRange(tokens, 1, tokens.length);
            }

            Executable executable = commandFactory.getCommand(command);
            String result = executable.execute(newParams);
            System.out.println(result);
        }
    }
}
