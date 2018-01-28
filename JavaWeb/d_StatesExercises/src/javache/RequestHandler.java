package javache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import db.UserRepositoryImpl;
import javache.http.HttpRequest;
import javache.http.HttpRequestImpl;
import javache.http.HttpResponse;
import javache.http.HttpResponseImpl;
import javache.http.HttpSession;
import javache.http.HttpStatus;
import javache.io.Reader;
import javache.models.User;
import javache.models.dtos.UserDTO;

public class RequestHandler {
    private static final String SESSION_KEY = "JAVACHE_SESSION_ID";
    private static final String RESOURCES_FOLDER = System.getProperty("user.dir") + "\\src\\resources\\";
    private static final String ASSETS_FOLDER = RESOURCES_FOLDER + "assets";

	private HttpRequest httpRequest;

    private HttpResponse httpResponse;
    
    private HttpSession session;

    public RequestHandler(HttpSession session) {
    	this.session = session;
    }
    
    @SuppressWarnings("serial")
	public byte[] handleRequest(String requestContent) {
        this.httpRequest = new HttpRequestImpl(requestContent);
        this.httpResponse = new HttpResponseImpl();

        String url = this.httpRequest.getRequestUrl();
        
        Map<String, String> bodyParams = this.httpRequest.getBodyParameters();
        switch (url.split("\\?")[0]) {
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
        	
			try {
				boolean isSaved = UserRepositoryImpl.save(new User(null, email, pass));
				
				if (!isSaved) {
	        		return this.BadRequest("<h1>User exists</h1>".getBytes());
	        	} else {
	        		this.httpResponse.addHeader("Location", "/html/login.html");
					return this.Redirect(new byte[0]);
	        	}
			} catch (IOException e2) {
				return this.InternalServerError("<h1>500 Error</h1>".getBytes());
			}
        	
        case "/users/login":
        	String loginEmail = bodyParams.get("email");
        	String loginPass = bodyParams.get("password");
        	try {
				User user = UserRepositoryImpl.findUserDataByEmail(loginEmail);
				
				if (user == null) {
					return this.BadRequest("<h1>User not found</h1>".getBytes());
				}
				
				if (!user.getPassword().equals(loginPass)) {
					return this.BadRequest("<h1>Incorrect password</h1>".getBytes());
				}
				
				String sessionID = UUID.randomUUID().toString();
				
				this.session.setSessionData(sessionID, new HashMap<String, Object>() {{
					put("userId", user.getId());
				}});
				
				this.httpResponse.addCookie(SESSION_KEY, sessionID);
				this.httpResponse.addHeader("Location", "/users/profile");
				
				return this.Redirect(new byte[0]);
			} catch (IOException e1) {
				e1.printStackTrace();
				return this.BadRequest("<h1>User not found</h1>".getBytes());
			}
        	
        case "/users/profile":
        	if (this.httpRequest.getCookies().containsKey(SESSION_KEY)) {
        		String sessionId = this.httpRequest.getCookies().get(SESSION_KEY);
        		String userId = (String) this.session.getSessionData(sessionId).get("userId");
				try {
					User user = UserRepositoryImpl.findUserDataById(userId);
					if (user == null) {
						byte[] guestPage = Files.readAllBytes(Paths.get(RESOURCES_FOLDER + "pages\\profile\\guest.html"));
						return this.Ok(guestPage);
					}
					
					String loggedContent = Reader.readAllLines(new FileInputStream(RESOURCES_FOLDER + "pages\\profile\\logged.html"));
					String loggedResponse = String.format(loggedContent, user.getName(), user.getPassword(), user.getName());
				
					return this.Ok(loggedResponse.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
        	} else {
        		try {
					byte[] guestPage = Files.readAllBytes(Paths.get(RESOURCES_FOLDER + "pages\\profile\\guest.html"));
					return this.Ok(guestPage);
        		} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        case "/home":    	
        	
        	try {
				Set<UserDTO> allUsers = UserRepositoryImpl.getAllUsers();
				String urlTokens[] = this.httpRequest.getRequestUrl().split("\\?");
	        	String[] userData = urlTokens[1].split("=");
	        	String emailUser = userData[1]; 
				StringBuilder userEmails = new StringBuilder();
				
				
				for (UserDTO userDTO : allUsers) {
					if (!userDTO.getEmail().equals(emailUser)) {
						userEmails.append(userDTO.getEmail() + "<br />");
					}
				}
				String homeContent = Reader.readAllLines(new FileInputStream(RESOURCES_FOLDER + "pages\\profile\\home.html"));
				String homeResponse = String.format(homeContent, userEmails);
				return this.Ok(homeResponse.getBytes());				
        		
			} catch (IOException e1) {
				e1.printStackTrace();
    			return this.BadRequest("<h1>Error 400: Malformed Request...</h1>".getBytes());
			}
    	default:
    		File file = new File(ASSETS_FOLDER + url);
    		if (!file.exists() || file.isDirectory()) {
    			return this.NotFound("<h1>Error 404: Page or Resource not found...</h1>".getBytes());
    		}
    		
    		try {
				if (!file.getCanonicalPath().startsWith(ASSETS_FOLDER)) {
					return this.BadRequest("<h1>Error 400: Malformed Request...</h1>".getBytes());
				}
				byte[] contents = Files.readAllBytes(Paths.get(ASSETS_FOLDER + this.httpRequest.getRequestUrl()));
				return this.Ok(contents);
    		} catch (IOException e) {
    			return this.BadRequest("<h1>Error 400: Malformed Request...</h1>".getBytes());
			}
        }

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
}
