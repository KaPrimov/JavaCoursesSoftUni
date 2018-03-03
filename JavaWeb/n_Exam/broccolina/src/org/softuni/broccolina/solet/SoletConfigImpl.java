package org.softuni.broccolina.solet;

import java.util.HashMap;

public class SoletConfigImpl implements SoletConfig {
    private HashMap<String, Object> attributes;

    public SoletConfigImpl() {
        this.attributes = new HashMap<>();
    }

    public Object getAttribute(String attributeName) {
        return attributes.getOrDefault(attributeName, null);
    }

    public void setAttribute(String key, Object value) {
        this.attributes.putIfAbsent(key, value);
    }
}
