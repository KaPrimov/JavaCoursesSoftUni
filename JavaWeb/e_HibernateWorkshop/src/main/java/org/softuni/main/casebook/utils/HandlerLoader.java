package org.softuni.main.casebook.utils;

import org.softuni.main.casebook.annotations.ApplicationRequestHandler;
import org.softuni.main.casebook.annotations.Get;
import org.softuni.main.casebook.annotations.Post;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class HandlerLoader {
    private static final String DYNAMIC_HANDLERS_FULL_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\org\\softuni\\main\\casebook\\handlers\\dynamic";
    private static final String DYNAMIC_HANDLERS_PACKAGE = "org.softuni.main.casebook.handlers.dynamic";

    private HashMap<String, HashMap<String, Method>> actionsMap;

    public HandlerLoader() {
        this.actionsMap = new HashMap<>();
        this.initializeSupportedMethods();
        this.loadMaps();
    }

    private void initializeSupportedMethods() {
        this.actionsMap.put("GET", new HashMap<>());
        this.actionsMap.put("POST", new HashMap<>());
    }

    private void loadMaps() {
        File directory = new File(DYNAMIC_HANDLERS_FULL_PATH);

        List<Class<?>> classes = Arrays.asList(directory.listFiles())
                .stream()
                .map(x -> {
                    try {
                        String fileFullName = String.valueOf(x);
                        String className = fileFullName.substring(fileFullName.lastIndexOf("\\") + 1).replace(".java", "");

                        Class<?> foundClass = Class.forName(DYNAMIC_HANDLERS_PACKAGE + "." + className);
                        if(foundClass.getAnnotation(ApplicationRequestHandler.class) != null) {
                        	return foundClass;                  	
                        }
                        
                        return null;
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(x -> x != null)
                .collect(Collectors.toList());

        for (Class loadedClass : classes) {
            for (Method method : loadedClass.getDeclaredMethods()) {
                method.setAccessible(true);

                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    if (annotation.annotationType().getSimpleName().equals("Get")) {
                        this.actionsMap.get("GET").put(((Get) annotation).route(), method);
                    } else if (annotation.annotationType().getSimpleName().equals("Post")) {
                        this.actionsMap.get("POST").put(((Post) annotation).route(), method);
                    }
                }
            }
        }
    }

    public Map<String, Method> retrieveActionsMap(String method) {
        if(!actionsMap.containsKey(method)) {
            return null;
        }

        return Collections.unmodifiableMap(this.actionsMap.get(method));
    }
}
