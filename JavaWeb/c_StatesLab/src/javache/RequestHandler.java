package javache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javache.http.HttpRequest;
import javache.http.HttpRequestImpl;
import javache.http.HttpResponse;
import javache.http.HttpResponseImpl;
import javache.http.HttpStatus;
import javache.models.User;

public class RequestHandler {
    private static final String DB_PATH = System.getProperty("user.dir") + "\\src\\resources\\db\\users.txt";
    private static final String ASSETS_FOLDER = System.getProperty("user.dir") + "\\src\\resources\\assets";

	private HttpRequest httpRequest;

    private HttpResponse httpResponse;

    public RequestHandler() { }
    
    public byte[] handleRequest(String requestContent) {
        this.httpRequest = new HttpRequestImpl(requestContent);
        this.httpResponse = new HttpResponseImpl();

        String url = this.httpRequest.getRequestUrl();
        
        Map<String, String> bodyParams = this.httpRequest.getBodyParameters();
        switch (url) {
        case "/":
        	try {
				byte[] fileContent = Files.readAllBytes(Paths.get(ASSETS_FOLDER + "\\html\\index.html"));
				return this.Ok(fileContent);
        	} catch (IOException e1) {
        		e1.printStackTrace();
				return this.InternalServerError(new byte[0]);
			}
        case "/users/register":
        	String email = bodyParams.get("email");
        	String pass = bodyParams.get("password");
        	String confirm = bodyParams.get("confirm");
        	
        	if (!pass.equals(confirm)) {
        		return this.BadRequest("<h1>Passwords does not match</h1>".getBytes());
        	}
        	
        	try(BufferedWriter writer = new BufferedWriter(new FileWriter(DB_PATH, true))) {
				User existingUser = this.findUserData(email);
				if (existingUser != null) {
					return this.BadRequest("<h1>User exists</h1>".getBytes());
				}
				
				writer.write(UUID.randomUUID().toString() + "|" + email + "|" + pass + System.lineSeparator());
				
				this.httpResponse.addHeader("Location", "/html/login.html");
				return this.Redirect(new byte[0]);
			} catch (IOException e1) {
				e1.printStackTrace();
				return this.InternalServerError(new byte[0]);
			}
        case "/users/login":
        	String loginEmail = bodyParams.get("email");
        	String loginPass = bodyParams.get("password");
        	try {
				User user = this.findUserData(loginEmail);
				
				if (user == null) {
					return this.BadRequest("<h1>User not found</h1>".getBytes());
				}
				
				if (!user.getPassword().equals(loginPass)) {
					return this.BadRequest("<h1>Incorrect password</h1>".getBytes());
				}
				
				
			} catch (IOException e1) {
				e1.printStackTrace();
				return this.BadRequest("<h1>User not found</h1>".getBytes());
			}
        	
        case "/users/profile":
        	return this.Ok("<h1>Profile</h1>".getBytes());
    	default:
    		File file = new File(ASSETS_FOLDER + url);
    		if (!file.exists() || file.isDirectory()) {
    			return this.NotFound(new byte[0]);
    		}
    		
    		try {
				if (!file.getCanonicalPath().startsWith(ASSETS_FOLDER)) {
					return this.BadRequest(new byte[0]);
				}
				byte[] contents = Files.readAllBytes(Paths.get(ASSETS_FOLDER + this.httpRequest.getRequestUrl()));
				return this.Ok(contents);
    		} catch (IOException e) {
    			return this.NotFound(new byte[0]);
			}
        }
//        if (this.httpRequest.isResource()) {
//        	try {
//        		byte[] contents = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "\\src\\resources\\assets" + this.httpRequest.getRequestUrl()));
//        		return this.Ok(contents);
//        	} catch (IOException e) {
//				return this.BadRequest(null);
//			}
//        } else {
//        	return this.Ok(new byte[0]);
//     	
//        }

    }

    private byte[] Ok(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.Ok);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] BadRequest(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.BadRequest);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] NotFound(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.NotFound);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] Redirect(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.SeeOther);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] InternalServerError(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.InternalServerError);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }
    
    
    private User findUserData(String email) throws IOException {
		String dbPath = DB_PATH;
    	
    	try (BufferedReader reader = new BufferedReader(new FileReader(dbPath))) {
    		String line = "";
    		while((line = reader.readLine()) != null) {
    			String[] userData = line.split("\\|");
				if (userData[1].equals(email)) {
					return new User(userData[1], userData[2]); 
				}
    		}
    	} 
    	return null;
    }
}
