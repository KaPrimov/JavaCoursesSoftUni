package warningLevels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Logger logger = new Logger(Importance.valueOf(reader.readLine()));
        while (true) {
            String line = reader.readLine();
            if(line.equals("END")) {
                break;
            }

            String[] tokens = line.split(":");
            logger.log(new Message(Importance.valueOf(tokens[0].trim()), tokens[1].trim()));

        }

        for (Message message : logger.getMessages()) {
            System.out.println(message);
        }
    }
}
