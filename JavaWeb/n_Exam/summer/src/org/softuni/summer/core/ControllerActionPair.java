package org.softuni.summer.core;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class ControllerActionPair {
    private Object controller;

    private Method action;

    private LinkedHashSet<Object> actionParameters;

    public ControllerActionPair(Object controller, Method action) {
        this.controller = controller;
        this.action = action;
        this.actionParameters = new LinkedHashSet<>();
    }

    public Object getController() {
        return this.controller;
    }

    public Method getAction() {
        return this.action;
    }

    public Set getParameters() {
        return Collections.unmodifiableSet(this.actionParameters);
    }

    public void addParameter(Object parameter) {
        this.actionParameters.add(parameter);
    }

    public void clearParameters() { this.actionParameters.clear(); }
}
