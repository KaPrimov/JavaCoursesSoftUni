package org.softuni.summer.core;

import org.softuni.summer.api.Controller;
import org.softuni.summer.api.PathVariable;
import org.softuni.summer.util.PathFormatter;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ControllerActionManager {
    private final String classFolderPath;

    private HashMap<String, HashMap<String, ControllerActionPair>> mappingTablesByMappingType;

    public ControllerActionManager(String classFolderPath) {
        this.classFolderPath = classFolderPath;
        this.initMappingTables();
    }

    private void initMappingTables() {
        this.mappingTablesByMappingType = new HashMap<>();

        this.mappingTablesByMappingType.putIfAbsent("GET", new HashMap<>());
        this.mappingTablesByMappingType.putIfAbsent("POST", new HashMap<>());
    }

    private void loadClass(File classFile, String packageName, ClassLoader ucl) {
        packageName = packageName.equals("classes.")
                ? ""
                : packageName;

        if (classFile.isDirectory()) {
            for (File childClassFile : classFile.listFiles()) {
                this.loadClass(childClassFile, packageName
                        + classFile.getName()
                        + ".", ucl);
            }
        } else {
            if (!classFile.getName().endsWith(".class")) return;

            try {
                String className = packageName + classFile.getName()
                        .replace(".class", "")
                        .replace("/", ".");

                Class clazzFile = ucl.loadClass(className);

                if (Arrays.stream(clazzFile.getAnnotations())
                        .anyMatch(x -> x
                                .annotationType()
                                .getSimpleName()
                                .equals(Controller.class.getSimpleName()))) {
                    Object controllerInstance = clazzFile
                            .getDeclaredConstructor()
                            .newInstance();

                    for (Method method : clazzFile.getDeclaredMethods()) {
                        for (Annotation annotation : method.getAnnotations()) {
                            String annotationName = annotation.annotationType().getSimpleName();
                            String annotationMapping = annotationName.replace("Mapping", "").toUpperCase();

                            if (Pattern
                                    .compile("^(Get|Post)Mapping")
                                    .matcher(annotationName)
                                    .find()
                                    && this.mappingTablesByMappingType
                                    .containsKey(annotationMapping)) {
                                String annotationRoute =
                                        annotation
                                                .getClass()
                                                .getDeclaredMethod("route")
                                                .invoke(annotation)
                                                .toString();

                                this.mappingTablesByMappingType.get(annotationMapping)
                                        .putIfAbsent(annotationRoute, new ControllerActionPair(controllerInstance, method));
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadControllers() {
        File classDirectory = new File(this.classFolderPath);

        if (!classDirectory.exists()) return;

        URL[] urls;

        try {
            urls = new URL[]{new File(this.classFolderPath).toURI().toURL()};
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

        URLClassLoader ucl = URLClassLoader.newInstance(urls, Thread.currentThread().getContextClassLoader());
        Thread.currentThread().setContextClassLoader(ucl);

        this.loadClass(classDirectory, "", ucl);
    }

    public ControllerActionPair getControllerActionPair(String requestMethod
            , String requestUrl) {
        PathFormatter pathFormatter = new PathFormatter();

        for (Map.Entry<String, ControllerActionPair> capEntry : this.mappingTablesByMappingType.get(requestMethod).entrySet()) {
            boolean isValidAction = true;

            String pathPattern = pathFormatter.formatPattern(capEntry.getKey());

            Matcher matcher = Pattern.compile(pathPattern).matcher(requestUrl);

            if (matcher.find()) {
                List<Parameter> actionParams = new ArrayList<Parameter>();

                for (Parameter parameter : capEntry.getValue().getAction().getParameters()) {
                    if(Arrays.stream(parameter.getAnnotations())
                            .anyMatch(x -> x.annotationType()
                                    .getSimpleName().equals(PathVariable.class.getSimpleName()))) {
                        actionParams.add(parameter);
                    }
                }
                
                for (int i = 0; i < matcher.groupCount() && i < actionParams.size(); i++) {
                    String parameterValue = matcher.group(i + 1);
                    Parameter currentParameter = actionParams.get(i);

                    try {
                        if (currentParameter.getParameterizedType().equals(String.class)) {
                            capEntry.getValue().addParameter(parameterValue);
                        } else if (currentParameter.getParameterizedType().equals(int.class)
                                || currentParameter.getParameterizedType().equals(Integer.class)) {
                            capEntry.getValue().addParameter(Integer.parseInt(parameterValue));
                        } else if (currentParameter.getParameterizedType().equals(long.class)
                                || currentParameter.getParameterizedType().equals(Long.class)) {
                            capEntry.getValue().addParameter(Long.parseLong(parameterValue));
                        }
                    } catch(NumberFormatException e) {
                        isValidAction = false;
                        break;
                    }
                }

                if(!isValidAction) continue;

                return capEntry.getValue();
            }
        }

        return null;
    }
}
