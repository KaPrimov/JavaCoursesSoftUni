package org.softuni.main.casebook.handlers.dynamic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

abstract class BaseHandler {
    private static final String VIEWS_FULL_PATH =
            System.getProperty("user.dir")
            + "\\src\\main\\java\\org\\softuni\\main\\casebook\\resources\\templates\\";

    private static final String VIEWS_EXTENSION = ".html";

    protected BaseHandler() {}

    protected String getView(String viewName) {
        try {
            List<String> content =
                    Files.readAllLines(Paths.get(
                            VIEWS_FULL_PATH
                                    + viewName
                                    + VIEWS_EXTENSION));
            return String.join("", content);
        } catch (IOException e) {
            return null;
        }
    }
}
