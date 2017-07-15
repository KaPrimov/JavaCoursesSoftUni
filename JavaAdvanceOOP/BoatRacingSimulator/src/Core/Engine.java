package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {
    private CommandHandlerImpl commandHandlerImpl;

    public Engine(CommandHandlerImpl commandHandlerImpl)
    {
        this.commandHandlerImpl = commandHandlerImpl;
    }


    public void Run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        while (true)
        {

            if (line.equals("End")) {
                break;
            }
            String name = "";
            List<String> parameters = new ArrayList<>();

            List<String> tokens = Arrays.asList(line.split("\\\\"));
            name = tokens.get(0);
            parameters = tokens.stream().skip(1).collect(Collectors.toList());

            try
            {
                String commandResult = this.commandHandlerImpl.executeCommand(name, parameters);
                System.out.println(commandResult);
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }

            line = reader.readLine();
        }
    }
}
