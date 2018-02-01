package org.softuni.main.javache;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.FutureTask;

import org.softuni.main.javache.http.HttpSessionStorage;
import org.softuni.main.javache.http.HttpSessionStorageImpl;

public class Server {
	 private static final String LISTENING_MESSAGE = "Listening on port: ";

	    private static final String TIMEOUT_DETECTION_MESSAGE = "Timeout detected!";

	    private static final Integer SOCKET_TIMEOUT_MILLISECONDS = 5000;

	    private int port;

	    private int timeouts;

	    private ServerSocket server;

	    private Application application;

	    public Server(int port, Application application) {
	        this.port = port;
	        this.timeouts = 0;
	        this.application = application;
	    }

	    public void run() throws IOException {
	        this.server = new ServerSocket(this.port);
	        System.out.println(LISTENING_MESSAGE + this.port);

	        this.server.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

	        HttpSessionStorage sessionStorage = new HttpSessionStorageImpl();
	        this.application.setSessionStorage(sessionStorage);
	        this.application.initializeRoutes();

	        while(true) {
	            try(Socket clientSocket = this.server.accept()) {
	                clientSocket.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

	                ConnectionHandler connectionHandler
	                        = new ConnectionHandler(clientSocket, new RequestHandler(application));

	                FutureTask<?> task = new FutureTask<>(connectionHandler, null);
	                task.run();
	            } catch(SocketTimeoutException e) {
	                System.out.println(TIMEOUT_DETECTION_MESSAGE);
	                this.timeouts++;
	            }
	        }
	    }
}