package org.softuni.summer.core;

import java.lang.reflect.Method;

public class ControllerActionPair {
    private Object controller;

    private Method action;

    public ControllerActionPair(Object controller, Method action) {
        this.controller = controller;
        this.action = action;
    }

    public Object getController() {
        return this.controller;
    }

    public Method getAction() {
        return this.action;
    }
}
