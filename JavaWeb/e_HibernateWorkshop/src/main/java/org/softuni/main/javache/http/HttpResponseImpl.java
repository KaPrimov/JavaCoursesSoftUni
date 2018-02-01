package org.softuni.main.javache.http;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpResponseImpl implements HttpResponse {
    private HttpStatus statusCode;

    private HashMap<String, String> headers;

    private HashMap<String, HttpCookie> cookies;

    private byte[] content;

    public HttpResponseImpl() {
        this.setContent(new byte[0]);
        this.headers = new HashMap<>();
        this.cookies = new HashMap<>();
    }

    private byte[] getHeadersBytes() {
        StringBuilder result = new StringBuilder()
                .append(ResponseLines.getResponseLine(this.getStatusCode().getStatusCode())).append(System.lineSeparator());

        for (Map.Entry<String,String> header : this.getHeaders().entrySet()) {
            result.append(header.getKey()).append(": ").append(header.getValue()).append(System.lineSeparator());
        }

        if(this.getCookies().size() > 0) {
            result.append("Set-Cookie")
                    .append(": ")
                    .append(
                            String.join("; ", this.getCookies()
                                    .entrySet()
                                    .stream()
                                    .map(x -> x.getValue().toString())
                                    .collect(Collectors.toList())))
                    .append(System.lineSeparator());
        }

        result.append(System.lineSeparator());

        return result.toString().getBytes();
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public HashMap<String, HttpCookie> getCookies() { return this.cookies; }

    @Override
    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void setStatusCode(HttpStatus status) {
        this.statusCode = status;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    @Override
    public void addCookie(HttpCookie cookie) {
        this.cookies.putIfAbsent(cookie.getName(), cookie);
    }

    @Override
    public byte[] getBytes() {
        byte[] headersBytes = this.getHeadersBytes();
        byte[] bodyBytes = this.getContent();

        byte[] fullResponse = new byte[headersBytes.length + bodyBytes.length];

        System.arraycopy(headersBytes, 0, fullResponse, 0, headersBytes.length);
        System.arraycopy(bodyBytes, 0, fullResponse, headersBytes.length, bodyBytes.length);

        return fullResponse;
    }
}
