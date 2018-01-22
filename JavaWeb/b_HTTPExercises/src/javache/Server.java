package javache;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.FutureTask;

public class Server {
	
	private int port;
	private ServerSocket server;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void run() throws IOException {
		this.server = new ServerSocket(this.port);
		this.server.setSoTimeout(WebConstants.SOCKET_TIMEOUT_MILISECONDS);
		
		while(true) {
			try (Socket clientSocket = this.server.accept()) {
				clientSocket.setSoTimeout(WebConstants.SOCKET_TIMEOUT_MILISECONDS);
				
				ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket, new RequestHandler());
				FutureTask<?> task = new FutureTask<>(connectionHandler, null);
				task.run();
			} catch (SocketTimeoutException ste) {
				System.out.println(ste.getMessage());
			}
		}
	}
	
}
