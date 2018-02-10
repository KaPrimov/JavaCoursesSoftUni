package solet;

import java.io.OutputStream;

import org.softuni.javache.http.HttpResponseImpl;

public class HttpSoletResponseImpl extends HttpResponseImpl implements HttpSoletResponse {

	private OutputStream responseStream;
		
	public HttpSoletResponseImpl(OutputStream responseStream) {
		this.responseStream = responseStream;
	}

	@Override
	public OutputStream getResponseStream() {
		// TODO Auto-generated method stub
		return this.responseStream;
	}
}
