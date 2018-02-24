package org.softuni.summer.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Model {
    private Map<String, Object> attributes;

    public Model(){
        this.attributes = new HashMap<>();
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    public void addAttribute(String key, Object attribute) {
        this.attributes.putIfAbsent(key, attribute);
    }
}
