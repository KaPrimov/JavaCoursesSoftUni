import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.softuni.javache.RequestHandler;
import org.softuni.javache.http.HttpResponse;
import org.softuni.javache.http.HttpResponseImpl;
import org.softuni.javache.http.HttpStatus;
import org.softuni.javache.io.Writer;

public class BroccoCustomHandler implements RequestHandler {
	
	private boolean hasIntercepted;
	private String serverRootPath;
	
	public BroccoCustomHandler(String serverRootPath) {
		this.hasIntercepted = false;
		this.serverRootPath = serverRootPath;
	}

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream) {
		try {
			HttpResponse httpResponse = new HttpResponseImpl();
			
			httpResponse.setStatusCode(HttpStatus.OK);
			httpResponse.addHeader("Content-Type", "text/html");
			httpResponse.setContent(("<h1>Hello from broccolina!!!</h1>" + this.serverRootPath).getBytes());
			
			Writer.writeBytes(httpResponse.getBytes(), outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public boolean hasIntercepted() {
		// TODO Auto-generated method stub
		return this.hasIntercepted;
	}

}
