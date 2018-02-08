package org.softuni.javache;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.softuni.javache.http.HttpSessionStorage;
import org.softuni.javache.http.HttpSessionStorageImpl;

public class Server {
    private static final String LISTENING_MESSAGE = "Listening on port: ";

    private static final String TIMEOUT_DETECTION_MESSAGE = "Timeout detected!";

    private static final Integer SOCKET_TIMEOUT_MILLISECONDS = 5000;

    private int port;

    private int timeouts;

    private ServerSocket server;
    
    private RequestHandlerLoader requestHandlerLoader;
    
    private ServerConfig serverConfig;

    public Server(int port) {
        this.port = port;
        this.timeouts = 0;
        this.serverConfig = new ServerConfig();
        this.requestHandlerLoader = new RequestHandlerLoader();
        this.initRequestHandlers();
        this.startLoadingProcess();
    }

    private void startLoadingProcess() {

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
          @Override
          public void run() {
            initRequestHandlers();
            System.out.println("loaded");
          }
        }, 0, 10, TimeUnit.SECONDS);		
	}

	private void initRequestHandlers() {
		this.requestHandlerLoader.loadRequestHandlers();
	}

	public void run() throws IOException {
        this.server = new ServerSocket(this.port);
        this.serverConfig.initializeConfig();
        System.out.println(LISTENING_MESSAGE + this.port);

        this.server.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

        HttpSessionStorage sessionStorage = new HttpSessionStorageImpl();
               
        while(true) {
            try(Socket clientSocket = this.server.accept()) {
                clientSocket.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

                ConnectionHandler connectionHandler
                        = new ConnectionHandler(clientSocket, this.requestHandlerLoader.getRequestHandlers(), this.serverConfig.getHandlers());

                FutureTask<?> task = new FutureTask<>(connectionHandler, null);
                task.run();
            } catch(SocketTimeoutException e) {
                System.out.println(TIMEOUT_DETECTION_MESSAGE);
                this.timeouts++;
            }
        }
    }
}