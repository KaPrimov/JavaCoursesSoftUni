package listIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandIterpreter commandIterpreter = new CommandIterpreter();
        while (true) {
            String[] tokens = reader.readLine().split("\\s+");
            if("END".equals(tokens[0])) {
                break;
            }

            commandIterpreter.acceptCommand(tokens);
        }
    }
}
