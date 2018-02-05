package org.softuni.javache.http;

import org.softuni.javache.WebConstants;

import java.util.Arrays;
import java.util.Map;

public interface HttpResponse {
    enum ResponseLines {
        OK (WebConstants.WEB_SERVER_HTTP_VERSION + " " + HttpStatus.OK.getStatusPhrase()),
        CREATED (WebConstants.WEB_SERVER_HTTP_VERSION+ " " + HttpStatus.CREATED.getStatusPhrase()),
        NO_CONTENT (WebConstants.WEB_SERVER_HTTP_VERSION + " " + HttpStatus.NO_CONTENT.getStatusPhrase()),
        SEE_OTHER (WebConstants.WEB_SERVER_HTTP_VERSION + " " + HttpStatus.SEE_OTHER.getStatusPhrase()),
        BAD_REQUEST (WebConstants.WEB_SERVER_HTTP_VERSION + " " + HttpStatus.BAD_REQUEST.getStatusPhrase()),
        UNAUTHORIZED (WebConstants.WEB_SERVER_HTTP_VERSION + " " + HttpStatus.UNAUTHORIZED.getStatusPhrase()),
        FORBIDDEN (WebConstants.WEB_SERVER_HTTP_VERSION + " " + HttpStatus.FORBIDDEN.getStatusPhrase()),
        NOT_FOUND (WebConstants.WEB_SERVER_HTTP_VERSION + " " + HttpStatus.NOT_FOUND.getStatusPhrase()),
        INTERNAL_SERVER_ERROR (WebConstants.WEB_SERVER_HTTP_VERSION + " " + HttpStatus.INTERNAL_SERVER_ERROR.getStatusPhrase());

        private String value;

        ResponseLines(String responseLine) {
            this.value = responseLine;
        }

        static String getResponseLine(int statusCode) {
            return ((ResponseLines) Arrays.stream(values()).filter((x) -> x.value.contains("" + statusCode)).toArray()[0]).value;
        }
    }

    Map<String, String> getHeaders();

    Map<String, HttpCookie> getCookies();

    HttpStatus getStatusCode();

    byte[] getContent();

    byte[] getBytes();

    void setStatusCode(HttpStatus statusCode);

    void setContent(byte[] content);

    void addHeader(String header, String value);

    void addCookie(HttpCookie cookie);
}
