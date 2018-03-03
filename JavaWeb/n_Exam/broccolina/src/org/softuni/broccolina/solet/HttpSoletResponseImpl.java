package org.softuni.broccolina.solet;

import org.softuni.javache.http.HttpResponseImpl;

import java.io.OutputStream;

public class HttpSoletResponseImpl extends HttpResponseImpl implements HttpSoletResponse {
    private OutputStream responseStream;

    public HttpSoletResponseImpl(OutputStream responseStream) {
        this.responseStream = responseStream;
    }

    @Override
    public OutputStream getResponseStream() {
        return this.responseStream;
    }
}
