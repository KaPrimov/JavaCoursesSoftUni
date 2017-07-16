package kingsGambit;

import kingsGambit.commands.AddFootmen;
import kingsGambit.commands.AddRoyalGuard;
import kingsGambit.commands.Command;
import kingsGambit.commands.CommandInterpreter;
import kingsGambit.models.King;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String kingName = reader.readLine();
        King king = new King(kingName);
        String[] royalGuardsName = reader.readLine().split("\\s+");
        String[] footmenNames = reader.readLine().split("\\s+");
        Command addRoaylGuards = new AddRoyalGuard(royalGuardsName, king);
        addRoaylGuards.execute();
        Command addFootmen = new AddFootmen(footmenNames, king);
        addFootmen.execute();

        String[] tokens = reader.readLine().split("\\s+");
        CommandInterpreter commandInterpreter = new CommandInterpreter();
        while (true) {
            if(tokens[0].equals("End")) {
                break;
            }
            commandInterpreter.executeCommand(tokens, king);
            tokens = reader.readLine().split("\\s+");

        }

    }
}
