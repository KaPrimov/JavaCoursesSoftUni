package org.softuni.summer.core;

import org.softuni.broccolina.solet.*;
import org.softuni.javache.http.HttpStatus;
import org.softuni.summer.api.Controller;
import org.softuni.summer.api.GetMapping;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

@WebSolet(route = "/*", loadOnStartUp = true)
public class DispatcherSolet extends BaseHttpSolet {
    private HashMap<String, ControllerActionPair> getMappingTable;

    private HashMap<String, ControllerActionPair> postMappingTable;

    private HashMap<String, ControllerActionPair> putMappingTable;

    private HashMap<String, ControllerActionPair> deleteMappingTable;

    public DispatcherSolet(SoletConfig soletConfig) {
        super(soletConfig);
        this.getMappingTable = new HashMap<>();
        this.postMappingTable = new HashMap<>();
        this.putMappingTable = new HashMap<>();
        this.deleteMappingTable = new HashMap<>();
    }

    private void loadClass(File classFile) {
        if (classFile.isDirectory()) {
            for (File childClassFile : classFile.listFiles()) {
                this.loadClass(childClassFile);
            }
        } else {
            if (!classFile.getName().endsWith(".class")) return;

            String parentPath = classFile.getParent();

            try {
                URL[] urls = new URL[]{new File(parentPath).toURI().toURL()};
                URLClassLoader ucl = URLClassLoader.newInstance(urls, Thread.currentThread().getContextClassLoader());

                Thread.currentThread().setContextClassLoader(ucl);

                String className = classFile.getName()
                        .replace(".class", "")
                        .replace("/", ".");

                Class clazzFile = ucl.loadClass(className);

                Object controllerInstance = clazzFile.getDeclaredConstructor().newInstance();

                if(Arrays.stream(clazzFile.getAnnotations())
                        .anyMatch(x -> x
                                .annotationType()
                                .getSimpleName()
                        .equals(Controller.class.getSimpleName()))) {
                    for (Method method : clazzFile.getDeclaredMethods()) {
                        for (Annotation annotation : method.getAnnotations()) {
                            if(annotation.annotationType().getSimpleName().equals(GetMapping.class.getSimpleName())) {
                                this.getMappingTable.putIfAbsent(((GetMapping)annotation).route(), new ControllerActionPair(controllerInstance, method));
                            }
                        }
                    }   
                }
            } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadControllers(String classFolderPath) {
        File classDirectory = new File(classFolderPath);

        if (!classDirectory.exists()) return;

        this.loadClass(classDirectory);
    }

    private boolean isTemplate(String result) {
        return result.startsWith("template:");
    }

    private String loadTemplate(String templateName) throws IOException {
        String templateFolder = this.getSoletConfig().getAttribute("application-path")
                + File.separator
                + "resources"
                + File.separator
                + "templates"
                + File.separator;

        String templateContent = String.join("", Files.readAllLines(
                Paths.get(templateFolder + templateName + ".html")));

        return templateContent;
    }

    @Override
    public void init() {
        super.init();

        String classFolderPath = this.getSoletConfig().getAttribute("application-path") + File.separator + "classes" + File.separator;

        this.loadControllers(classFolderPath);
    }

    @Override
    public void service(HttpSoletRequest request, HttpSoletResponse response) {
        response.setStatusCode(HttpStatus.OK);

        if(request.getMethod().equals("GET")) {
            ControllerActionPair cap = this.getMappingTable.get(request.getRequestUrl());

            String result = null;

            if(cap == null) {
                result = "Not Found";
            } else {
                try {
                    result = (String) cap
                            .getAction()
                            .invoke(cap.getController());

                    if(this.isTemplate(result)) {
                        response.addHeader("Content-Type", "text/html");

                        result = this.loadTemplate(result.split(":")[1]);
                    }

                } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                    response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

                    e.printStackTrace();

                    result = "VSICHKO GRUMNA";
                }
            }

            response.setContent(result.getBytes());
        }
    }
}
