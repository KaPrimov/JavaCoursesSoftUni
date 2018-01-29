package org.softuni.main;

import java.io.IOException;

import org.softuni.main.casebook.CasebookApplication;
import org.softuni.main.javache.Application;
import org.softuni.main.javache.Server;
import org.softuni.main.javache.WebConstants;

public class StartUp {
    public static void main(String[] args) {

         start(args);
    }

    private static void start(String[] args) {
        int port = WebConstants.DEFAULT_SERVER_PORT;

        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        Application application = new CasebookApplication();
        Server server = new Server(port, application);

        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
