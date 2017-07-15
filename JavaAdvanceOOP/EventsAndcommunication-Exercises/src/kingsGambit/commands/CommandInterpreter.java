package kingsGambit.commands;

import kingsGambit.groups.AttackableGroupImpl;

public class CommandInterpreter {

    public void executeCommand(String[] data, AttackableGroupImpl attackableGroup) {

        String commandString = data[0];
        Command command = null;
        switch (commandString) {
            case "Kill":
                command = new Kill(data, attackableGroup);
                command.execute();
                break;
            case "Attack":
                command = new Attack(attackableGroup);
                command.execute();
                break;
        }

    }

}
