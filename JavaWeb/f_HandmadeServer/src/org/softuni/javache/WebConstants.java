package org.softuni.javache;

public final class WebConstants {
	private static final String SERVER_FOLDER_PATH = "org/softuni/javache";
	
    public static final Integer DEFAULT_SERVER_PORT = 8000;

    public static final String WEB_SERVER_HTTP_VERSION = "HTTP/1.1";

    public static final String SERVER_SESSION_TOKEN = "Javache";
    
    public static final String SERVER_ROOT_PATH = Server.class.getResource("").getPath().substring(1).replace("/" + SERVER_FOLDER_PATH, "");
    
    private WebConstants() { }
}
