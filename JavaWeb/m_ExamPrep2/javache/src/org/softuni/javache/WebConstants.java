package org.softuni.javache;

public final class WebConstants {
    private static final String WEB_SERVER_PACKAGE_FOLDER_PATH = "org/softuni/javache/";

    public static final Integer DEFAULT_SERVER_PORT = 8000;

    public static final String WEB_SERVER_HTTP_VERSION = "HTTP/1.1";

    public static final String SERVER_SESSION_TOKEN = "Javache";

    public static final String WEB_SERVER_ROOT_FOLDER_PATH =
            Server
            .class
            .getResource("")
            .getPath()
            .replace(WEB_SERVER_PACKAGE_FOLDER_PATH, "")
            .substring(1);

    private WebConstants() { }
}
