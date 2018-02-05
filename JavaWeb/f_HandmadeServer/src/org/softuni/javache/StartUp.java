package org.softuni.javache;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StartUp {
    public static void main(String[] args) {
    	System.out.println(new RequestHandlerLoader().loadRequestHandlers());
         start(args);
    }

    private static void start(String[] args) {
        int port = WebConstants.DEFAULT_SERVER_PORT;

        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }
        Set<RequestHandler> requestHandlers = new HashSet<>();	
        Server server = new Server(port, requestHandlers);

        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
