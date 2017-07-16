package infernoInfinity.io;

import infernoInfinity.io.interfaces.Reader;

public class InputReader implements Reader {

    private static final String DELIMITER = ";";

    @Override
    public String[] readInputLine(String input) {
        String[] tokens = input.split(DELIMITER);
        return tokens;
    }
}
