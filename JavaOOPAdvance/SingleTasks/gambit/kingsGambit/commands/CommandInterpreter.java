package kingsGambit.commands;

import kingsGambit.models.King;

public class CommandInterpreter {

    public void executeCommand(String[] data, King king) {

        String commandString = data[0];
        Command command = null;
        switch (commandString) {
            case "Kill":
                command = new Kill(data, king);
                command.execute();
                break;
            case "Attack":
                command = new Attack(king);
                command.execute();
                break;
        }

    }

}
