package customList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        while (true) {
            String[] tokens = reader.readLine().split("\\s+");
            if(tokens[0].equals("END")) {
                break;
            }
            commandInterpreter.execute(tokens);
        }
    }
}
