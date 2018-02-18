package org.softuni.javache.util;

import org.softuni.javache.RequestHandler;
import org.softuni.javache.WebConstants;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class RequestHandlerLoader {
    private static final String LIB_FOLDER_PATH =
            WebConstants.WEB_SERVER_ROOT_FOLDER_PATH
            + "lib";

    private LinkedHashMap<String, RequestHandler> loadedRequestHandlers;

    private boolean isLibraryFile(File file) {
        return file.getName().endsWith(".jar");
    }

    private void loadLibrary(JarFile library, String canonicalPath) {
        Enumeration<JarEntry> fileEntries = library.entries();

        try {
            URL[] urls = { new URL("jar:file:" + canonicalPath + "!/") };
            URLClassLoader ucl = URLClassLoader.newInstance(urls);

            while(fileEntries.hasMoreElements()) {
                JarEntry currentFile = fileEntries.nextElement();

                if(currentFile.isDirectory()
                        || !currentFile.getName().endsWith(".class")) continue;

                String className = currentFile.getName()
                        .replace(".class", "")
                        .replace("/", ".");

                Class handlerClazz = ucl.loadClass(className);

                if(RequestHandler.class.isAssignableFrom(handlerClazz)) {
                    RequestHandler handlerObject =
                            (RequestHandler) handlerClazz.getConstructor(String.class)
                            .newInstance(WebConstants.WEB_SERVER_ROOT_FOLDER_PATH);

                    this.loadedRequestHandlers
                            .putIfAbsent(handlerObject
                                    .getClass()
                                    .getSimpleName(), handlerObject);
                }
            }
        } catch (MalformedURLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void loadLibraries(String libFolderPath, Set<String> requestHandlerPriority) throws IOException {
        File libDirectory = new File(libFolderPath);

        if(libDirectory.exists() && libDirectory.isDirectory()) {
            List<File> libDirectoryJarFiles =
                    Arrays
                            .stream(libDirectory.listFiles())
                            .filter(x -> this.isLibraryFile(x))
                            .collect(Collectors.toList());

            for (String priorityHandler : requestHandlerPriority) {
                File currentFile = libDirectoryJarFiles
                        .stream()
                        .filter(x -> x
                                .getName()
                                .replace(".jar", "")
                                .equals(priorityHandler))
                        .findFirst()
                        .orElse(null);

                if(currentFile != null) {
                    JarFile library = new JarFile(currentFile.getCanonicalPath());
                    this.loadLibrary(library, currentFile.getCanonicalPath());
                }
            }
        }
    }

    public Map<String, RequestHandler> getRequestHandlers() {
        return Collections.unmodifiableMap(this.loadedRequestHandlers);
    }

    public void loadRequestHandlers(Set<String> requestHandlerPriority) {
        this.loadedRequestHandlers = new LinkedHashMap<>();

        try {
            this.loadLibraries(LIB_FOLDER_PATH, requestHandlerPriority);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
