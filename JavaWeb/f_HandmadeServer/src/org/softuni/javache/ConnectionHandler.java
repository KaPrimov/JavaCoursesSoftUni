package org.softuni.javache;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

import org.softuni.javache.io.Reader;

public class ConnectionHandler extends Thread {
    private Socket clientSocket;

    private InputStream clientSocketInputStream;

    private OutputStream clientSocketOutputStream;
    
    private Map<String, RequestHandler> requestHandlers;
    
    private Set<String> requestHandlersByPriority;
    
    private String cachedStringContent;

    public ConnectionHandler(Socket clientSocket, Map<String, RequestHandler> requestHandlers, Set<String> requestHandlersByPriority) {
        this.initializeConnection(clientSocket);
        this.requestHandlers = requestHandlers;
        this.requestHandlersByPriority = requestHandlersByPriority;
    }

    @Override
    public void run() {
        try {
            this.processRequest();
            this.clientSocketInputStream.close();
            this.clientSocketOutputStream.close();
            this.clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

    public InputStream getClientSocketInputStream() throws IOException {
    	if (this.cachedStringContent == null) {
    		this.cachedStringContent = Reader.readAllLines(this.clientSocketInputStream);
    	}
		return new ByteArrayInputStream(cachedStringContent.getBytes());
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
		for (String requestHandlerName : this.requestHandlersByPriority) {			
			if (requestHandlers.containsKey(requestHandlerName)) {
				RequestHandler requestHandler = this.requestHandlers.get(requestHandlerName);
				requestHandler.handleRequest(this.getClientSocketInputStream(), clientSocketOutputStream);
				if (requestHandler.hasIntercepted()) {
					break;
				}	
			}					
		}		
	}
}






