package org.softuni.javache;

import org.softuni.javache.io.Reader;
import org.softuni.javache.util.StreamCachingService;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class ConnectionHandler extends Thread {
    private Socket clientSocket;

    private InputStream clientSocketInputStream;

    private OutputStream clientSocketOutputStream;

    private StreamCachingService cachingService;

    private Map<String, RequestHandler> requestHandlers;

    public ConnectionHandler(Socket clientSocket,
                             Map<String, RequestHandler> requestHandlers,
                             StreamCachingService cachingService) {
        this.initializeConnection(clientSocket);
        this.requestHandlers = requestHandlers;
        this.cachingService = cachingService;
    }

    private void initializeConnection(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.clientSocketInputStream = this.clientSocket.getInputStream();
            this.clientSocketOutputStream = this.clientSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRequest() throws IOException {
        for (RequestHandler requestHandler : this.requestHandlers.values()) {
            requestHandler
                    .handleRequest(this.cachingService.getOrCacheInputStream(this.clientSocketInputStream), this.clientSocketOutputStream);

            if (requestHandler.hasIntercepted()) {
                break;
            }
        }
    }

    @Override
    public void run() {
        try {
            this.processRequest();
            this.clientSocketInputStream.close();
            this.clientSocketOutputStream.close();
            this.clientSocket.close();
            this.cachingService.evictCache();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






