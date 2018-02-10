package solet;

import org.softuni.javache.http.HttpStatus;

public abstract class BaseHttpSolet implements HttpSolet {

	private static final String NOT_FOUND_MESSAGE = "<h1>Error: Not Found! %s</h1>";

	@Override
	public void doGet(HttpSoletRequest request, HttpSoletResponse response) {
		response.setStatusCode(HttpStatus.NOT_FOUND);
		response.addHeader("Content-Type", "text/html");
		response.setContent(String.format(NOT_FOUND_MESSAGE, "GET Action").getBytes());
	}

	@Override
	public void doPost(HttpSoletRequest request, HttpSoletResponse response) {
		response.setStatusCode(HttpStatus.NOT_FOUND);
		response.addHeader("Content-Type", "text/html");
		response.setContent(String.format(NOT_FOUND_MESSAGE, "POST Action").getBytes());
		
	}

	@Override
	public void doDelete(HttpSoletRequest request, HttpSoletResponse response) {
		response.setStatusCode(HttpStatus.NOT_FOUND);
		response.addHeader("Content-Type", "text/html");
		response.setContent(String.format(NOT_FOUND_MESSAGE, "DELETE Action").getBytes());
	}

	@Override
	public void doPut(HttpSoletRequest request, HttpSoletResponse response) {
		response.setStatusCode(HttpStatus.NOT_FOUND);
		response.addHeader("Content-Type", "text/html");
		response.setContent(String.format(NOT_FOUND_MESSAGE, "PUT Action").getBytes());
	}

}
