package eventImplementation;

import eventImplementation.contracts.Dispatchable;
import eventImplementation.contracts.NameChangeListener;
import eventImplementation.models.Dispatcher;
import eventImplementation.models.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Dispatchable dispatcher = new Dispatcher();
        NameChangeListener event = new Handler();

        dispatcher.addNameChangeListener(event);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        while(!line.equals("End")){
            dispatcher.setName(line);
            line = reader.readLine();
        }
    }
}
