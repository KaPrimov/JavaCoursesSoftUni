package org.softuni.javache.http;

import java.util.Map;

public interface HttpSession {
    String getId();

    boolean isValid();

    void addAttribute(String attribute, Object value);

    Map<String, Object> getAttributes();

    void invalidate();
}
