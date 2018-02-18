package org.softuni.broccolina.solet;

import java.util.HashMap;
import java.util.Map;

public class SoletConfig {
    private Map<String, Object> attributes;

    public SoletConfig() {
        this.attributes = new HashMap<>();
    }


    public Object getAttribute(String attributeName) {
        return this.attributes.getOrDefault(attributeName, null);
    }

    public void setAttribute(String key, Object attribute) {
        this.attributes.putIfAbsent(key, attribute);
    }
}
