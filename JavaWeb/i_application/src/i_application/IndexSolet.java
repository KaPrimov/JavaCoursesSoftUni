package i_application;

import org.softuni.javache.http.HttpStatus;

import solet.BaseHttpSolet;
import solet.HttpSoletRequest;
import solet.HttpSoletResponse;
import solet.WebSolet;

@WebSolet(route = "/")
public class IndexSolet extends BaseHttpSolet{

	@Override
	public void doGet(HttpSoletRequest request, HttpSoletResponse response) {
		response.setStatusCode(HttpStatus.OK);
		response.addHeader("Content-Type", "text/html");
		response.setContent("<h1>Hi from index</h1>".getBytes());
	
	}
	
}
