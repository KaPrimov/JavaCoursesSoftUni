import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.softuni.javache.RequestHandler;
import org.softuni.javache.http.HttpRequest;
import org.softuni.javache.http.HttpRequestImpl;
import org.softuni.javache.io.Reader;
import org.softuni.javache.io.Writer;

import solet.HttpSolet;
import solet.HttpSoletRequestImpl;
import solet.HttpSoletResponse;
import solet.HttpSoletResponseImpl;
import solet.WebSolet;
import util.SoletLoader;

public class SoletDispatcher implements RequestHandler {
	
	private boolean hasIntercepted;
	private String serverRootPath;
	private SoletLoader soletLoader;
	
	public SoletDispatcher(String serverRootPath) {
		this.hasIntercepted = false;
		this.serverRootPath = serverRootPath;
		this.soletLoader = new SoletLoader(this.serverRootPath);
		this.soletLoader.loadSolets();
	}

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream) {
		
		try {
			HttpSoletResponse response = new HttpSoletResponseImpl(outputStream);
			String requestContent = Reader.readAllLines(inputStream);
			HttpRequest request = new HttpRequestImpl(requestContent);
			for (Map.Entry<String, HttpSolet> soletEntry: this.soletLoader.getSolets().entrySet()) {
				 String soletRoute = soletEntry.getValue().getClass().getDeclaredAnnotation(WebSolet.class).route();
				 
				 System.out.println("new" + soletRoute);
				 if (soletRoute.equals(request.getRequestUrl())) {
					 soletEntry.getValue().doGet(
							 new HttpSoletRequestImpl(requestContent, null),
							 response);
						Writer.writeBytes(response.getBytes(), outputStream);
				 }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean hasIntercepted() {
		return this.hasIntercepted;
	}

}
