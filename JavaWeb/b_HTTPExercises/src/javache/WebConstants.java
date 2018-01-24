package javache;

public final class WebConstants {
	
	public static final int SOCKET_TIMEOUT_MILISECONDS = 50000;
	public static final int DEFAULT_SERVER_PORT = 8321;
	
	public static final String OK_STATUS = "OK";
	public static final String FOUND_STATUS = "Found";
	public static final String NOT_MODIFIED_STATUS = "Not Modified";
	public static final String BAD_REQUEST_STATUS = "Bad Request";
	public static final String UNAUTHORIZED_STATUS = "Unathorized";
	public static final String NOT_FOUND_STATUS = "Not Found";
	public static final String INTERNAL_SERVER_ERROR_STATUS = "Internal Server Error";
	
	public static final Integer OK_STATUS_CODE = 200;
	public static final Integer FOUND_STATUS_CODE = 302;
	public static final Integer NOT_MODIFIED_STATUS_CODE = 304;
	public static final Integer BAD_REQUEST_STATUS_CODE = 400;
	public static final Integer UNAUTHORIZED_STATUS_CODE = 401;
	public static final Integer NOT_FOUND_CODE = 404;
	public static final Integer INTERNAL_SERVER_ERROR_STATUS_CODE = 500;
	
	private WebConstants() {}	
}
