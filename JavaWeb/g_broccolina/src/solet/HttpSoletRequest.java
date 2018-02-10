package solet;

import java.io.InputStream;

import org.softuni.javache.http.HttpRequest;

public interface HttpSoletRequest extends HttpRequest {
	InputStream getRequestStream();
}
