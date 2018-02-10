package solet;

import java.io.OutputStream;

import org.softuni.javache.http.HttpResponse;

public interface HttpSoletResponse extends HttpResponse {
	OutputStream getResponseStream();
}
