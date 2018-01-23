package javache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javache.http.HttpRequest;
import javache.http.HttpRequestImpl;
import javache.http.HttpResponse;
import javache.http.HttpResponseImpl;

public class RequestHandler {
	
	private static final String ASSETS_PATH = "E:\\Programming\\JavaCoursesSoftUni\\JavaWeb\\b_HTTPExercises\\src\\resources\\assets\\";
	private static final String HTMLS_PATH = "E:\\Programming\\JavaCoursesSoftUni\\JavaWeb\\b_HTTPExercises\\src\\resources\\pages\\";
	
	private HttpResponse httpResponse;
	private HttpRequest httpRequest;	
	private Map<String, String> supportedContentTypes;
	
	public RequestHandler() {
		this.supportedContentTypes = new HashMap<>();
		this.seedSupportedContentTypes();
	}


	public byte[] handleRequest(String requestContent) {
		this.httpRequest = new HttpRequestImpl(requestContent);
		this.httpResponse = new HttpResponseImpl();
		
		if (httpRequest.isResource()) {
			String ext = this.httpRequest.getRequestUri().substring(this.httpRequest.getRequestUri().lastIndexOf(".") + 1);
			if (this.supportedContentTypes.containsKey(ext)) {
				this.httpResponse.addHeader("Content-Type: ", this.supportedContentTypes.get(ext));
				byte[] content = null;
				
				try {
					content = Files.readAllBytes(Paths.get(ASSETS_PATH + this.httpRequest.getRequestUri()));
					this.httpResponse.setContent(content);
					return this.httpResponse.getBytes();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				this.httpResponse.addHeader("Content-Type: ", this.supportedContentTypes.get("html"));
				byte[] content;
				try {
					content = Files.readAllBytes(Paths.get(HTMLS_PATH + "not-found.html"));
					this.httpResponse.setContent(content);
					return this.httpResponse.getBytes();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			this.httpResponse.addHeader("Content-Type: ", this.supportedContentTypes.get("html"));
			if (this.httpRequest.getRequestUri().equals("/index")) {
				byte[] content;
				try {
					content = Files.readAllBytes(Paths.get(HTMLS_PATH + "page-content.html"));
					this.httpResponse.setContent(content);
					return this.httpResponse.getBytes();
				} catch (IOException e) {
					e.printStackTrace();
				}					
			} else if (this.httpRequest.getRequestUri().equals("/register")) {
				byte[] content;
				try {
					content = Files.readAllBytes(Paths.get(HTMLS_PATH + "simple-website.html"));
					this.httpResponse.setContent(content);
					return this.httpResponse.getBytes();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				byte[] content;
				try {
					content = Files.readAllBytes(Paths.get(HTMLS_PATH + "not-found.html"));
					this.httpResponse.setContent(content);
					return this.httpResponse.getBytes();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		return null;
	}	private void seedSupportedContentTypes() {
        this.supportedContentTypes.put("png", "image/png");
        this.supportedContentTypes.put("jpg", "image/jpeg");
        this.supportedContentTypes.put("jpeg", "image/jpeg");
        this.supportedContentTypes.put("html", "text/html");
    }

}
