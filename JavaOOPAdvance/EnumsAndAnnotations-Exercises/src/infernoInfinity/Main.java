package infernoInfinity;

import infernoInfinity.factories.CommandFactory;
import infernoInfinity.io.InputReader;
import infernoInfinity.io.OutputWriter;
import infernoInfinity.io.interfaces.Reader;
import infernoInfinity.io.interfaces.Writer;
import infernoInfinity.repositories.WeaponRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Reader inputReader = new InputReader();
        Writer outputWriter = new OutputWriter();
        WeaponRepository weaponRepository = new WeaponRepository();
        CommandFactory commandFactory = new CommandFactory(weaponRepository, outputWriter);
        String[] tokens = inputReader.readInputLine(reader.readLine());

            while (!"END".equals(tokens[0])) {

                commandFactory.executeCommand(tokens);

                tokens = inputReader.readInputLine(reader.readLine());
            }


    }
}
