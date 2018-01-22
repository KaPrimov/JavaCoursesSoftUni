package javache;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandler extends Thread {
	
	private Socket clientSocket;
	private OutputStream outputStream;
	private InputStream inputStream;
	private RequestHandler requestHandler;
	
	public ConnectionHandler(Socket clientSocket, RequestHandler requestHandler) {
		this.initializeConnection(clientSocket);
		this.requestHandler = requestHandler;
	}

	private void initializeConnection(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			this.inputStream = this.clientSocket.getInputStream();
			this.outputStream = this.clientSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
//		String requestContent = Reader.readAllLines(this.inputStream);
		
	}
	
	

}
