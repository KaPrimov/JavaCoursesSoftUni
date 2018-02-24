package org.softuni.broccolina.solet;

import org.softuni.javache.http.HttpCookie;
import org.softuni.javache.http.HttpRequestImpl;

import java.io.InputStream;
import java.util.Map;

public class HttpSoletRequestImpl extends HttpRequestImpl implements HttpSoletRequest {
    private InputStream requestStream;

    public HttpSoletRequestImpl(String requestContent, InputStream requestStream) {
        super(requestContent);
        this.requestStream = requestStream;
    }

    @Override
    public InputStream getRequestStream() {
        return this.requestStream;
    }
}
