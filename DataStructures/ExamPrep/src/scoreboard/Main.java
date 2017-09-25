package scoreboard;

import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("End")) {
                break;
            }
            if (!command.equals("")) {
                String commandResult = commandExecutor.processCommand(command);
                System.out.println(commandResult);
            }
        }
    }
}
