

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.softuni.javache.RequestHandler;
import org.softuni.javache.http.HttpRequest;
import org.softuni.javache.http.HttpRequestImpl;
import org.softuni.javache.http.HttpResponse;
import org.softuni.javache.http.HttpResponseImpl;
import org.softuni.javache.http.HttpStatus;
import org.softuni.javache.io.Reader;
import org.softuni.javache.io.Writer;

public class ToyoteStaticResourceHandler implements RequestHandler {

	
	private static final String STATIC_FOLDER_NAME = "static";
	private boolean hasIntercepted;
	private String serverRootPath;
	
	public ToyoteStaticResourceHandler(String serverRootPath) {
		this.hasIntercepted = false;
		this.serverRootPath = serverRootPath;
	}
	@Override
	public void handleRequest(InputStream input, OutputStream output) {
		 try {
				String lines = Reader.readAllLines(input);
				HttpRequest request = new HttpRequestImpl(lines);
	            String resourceUrl = request.getRequestUrl();
	            if (resourceUrl.indexOf(".") == -1) {
	            	this.hasIntercepted = false;
	            	return;
	            }
	            String resourceExtension = resourceUrl.substring(resourceUrl.lastIndexOf(".") + 1);
	            byte[] content =
	                    Files.readAllBytes(Paths.get(this.serverRootPath + STATIC_FOLDER_NAME + resourceUrl));
	            HttpResponse httpResponse = new HttpResponseImpl();
				
				httpResponse.setStatusCode(HttpStatus.OK);
				httpResponse.addHeader("Content-Type", this.getContentType(resourceExtension));
				httpResponse.addHeader("Content-Length", "" + content.length);
				httpResponse.addHeader("Content-Disposition", "inline");
				httpResponse.setContent(content);
				
				Writer.writeBytes(httpResponse.getBytes(), output);
				this.hasIntercepted = true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            this.hasIntercepted  = false;
	        }

	}

	@Override
	public boolean hasIntercepted() {
		return this.hasIntercepted;
	}
	
	 private String getContentType(String resourceExtension) {
	        switch(resourceExtension) {
	            case "html": return "text/html";
	            case "css": return "text/css";
	            case "png": return "image/png";
	            case "jpg":
	            case "jpeg":
	                    return "image/jpeg";
	            case "ico": return "image/x-icon";
	            default: return "text/plain";
	        }
	    }

}
