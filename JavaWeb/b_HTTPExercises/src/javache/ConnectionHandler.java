package javache;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javache.io.Reader;
import javache.io.Writer;

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
		try {
			String requestContent = Reader.readAllLines(this.inputStream);
			byte[] responseContent = this.requestHandler.handleRequest(requestContent);
			Writer.writeBytes(responseContent, this.outputStream);
			this.inputStream.close();
			this.outputStream.close();
			this.clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
