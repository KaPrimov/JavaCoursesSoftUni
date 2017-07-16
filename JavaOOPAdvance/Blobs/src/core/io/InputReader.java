package core.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputReader {

    private static final String END_COMMAND = "drop";

    private CommandInterpreter interpreter;
    private OutputWriter outputWriter;

    public InputReader(CommandInterpreter interpreter, OutputWriter outputWriter) {
        this.interpreter = interpreter;
        this.outputWriter = outputWriter;
    }

    public void readCommands() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine().trim();

        while (!input.equals(END_COMMAND)) {
            this.interpreter.interpretCommand(input);

            input = reader.readLine().trim();
        }

    }
}
