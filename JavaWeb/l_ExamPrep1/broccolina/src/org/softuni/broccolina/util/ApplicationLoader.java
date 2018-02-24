package org.softuni.broccolina.util;

import org.softuni.broccolina.solet.HttpSolet;
import org.softuni.broccolina.solet.SoletConfig;
import org.softuni.broccolina.solet.SoletConfigImpl;
import org.softuni.broccolina.solet.WebSolet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ApplicationLoader {
    private static final String ROOT_APPLICATION_FOLDER_NAME = "ROOT";

    public final String APPLICATION_FOLDER_PATH;

    private JarUnzipUtil jarUnzipUtil;

    private HashMap<String, Object> loadedSoletsByApplicationName;

    public ApplicationLoader(String serverRootPath) {
        this.APPLICATION_FOLDER_PATH =
                serverRootPath + "apps";
        this.jarUnzipUtil = new JarUnzipUtil();
    }

    private boolean isJarFile(File file) {
        return file.getName().endsWith(".jar");
    }

    private void unzipJar(File jarFile) throws IOException {
        this.jarUnzipUtil.unzipJar(jarFile.getCanonicalPath());
    }

    private Object getSoletConfig(Class soletClass, String applicationPath) {
        SoletConfig soletConfigImpl = new SoletConfigImpl();

        soletConfigImpl.setAttribute("application-path", applicationPath);

        Class[] requiredParameters = Arrays.stream(soletClass
                .getDeclaredConstructors())
                .filter((x) -> x.getParameterCount() == 1)
                .findFirst()
                .get()
                .getParameterTypes();

        Object proxySoletConfig = Proxy.newProxyInstance(
                soletClass.getClassLoader(),
                new Class[]{requiredParameters[0]},
                (proxy, method, args) -> Arrays.stream(soletConfigImpl
                        .getClass()
                        .getMethods())
                        .filter((x) -> x.getName().equals(method.getName()))
                        .findFirst()
                        .get()
                        .invoke(soletConfigImpl, args));

        return proxySoletConfig;
    }

    private void loadIfSolet(Class clazzFile, String applicationName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class parentClass = clazzFile.getSuperclass();

        if (parentClass != null
                && Arrays.stream(parentClass.getInterfaces())
                .anyMatch((x) -> x
                        .getSimpleName()
                        .equals(HttpSolet.class.getSimpleName()))
                && !Modifier.isAbstract(clazzFile.getModifiers())) {

            Object soletConfigObject = this.getSoletConfig(clazzFile, this.APPLICATION_FOLDER_PATH + File.separator + applicationName);

            Object soletObject =
                    Arrays.stream(clazzFile
                            .getDeclaredConstructors())
                            .filter(x -> x.getParameterCount() == 1)
                            .findFirst()
                            .get()
                            .newInstance(soletConfigObject);

            Object annotation = Arrays.stream(clazzFile.getDeclaredAnnotations())
                    .filter((x) -> x
                            .annotationType()
                            .getSimpleName()
                            .equals(WebSolet.class.getSimpleName()))
                    .findFirst()
                    .orElse(null);

            if(annotation == null) return;

            if((boolean)annotation
                    .getClass()
                    .getMethod("loadOnStartUp")
                    .invoke(annotation)){
                soletObject.getClass().getMethod("init").invoke(soletObject);
            }

            applicationName = applicationName.equals(ROOT_APPLICATION_FOLDER_NAME)
                    ? ""
                    : "/" + applicationName;

            String annotationRoute = annotation
                    .getClass()
                    .getMethod("route")
                    .invoke(annotation)
                    .toString();

            this.loadedSoletsByApplicationName
                    .putIfAbsent(applicationName + annotationRoute
                            , soletObject);
        }
    }

    private void loadClass(File classFile, String packageName, ClassLoader ucl, String applicationName) {
        packageName = packageName.equals("classes.")
                ? ""
                : packageName;

        if(classFile.isDirectory()) {
            for (File childClassFile : classFile.listFiles()) {
                this.loadClass(childClassFile
                        , packageName + classFile.getName() + "."
                        , ucl
                        , applicationName);
            }
        } else {
            if(!classFile.getName().endsWith(".class")) return;

            try {
                String className = packageName + classFile.getName()
                        .replace(".class", "")
                        .replace("/", ".");

                Class clazzFile = ucl.loadClass(className);

                this.loadIfSolet(clazzFile, applicationName);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLibrary(JarFile library, String canonicalPath, String applicationName) {
        Enumeration<JarEntry> fileEntries = library.entries();

        try {
            URL[] urls = {new URL("jar:file:" + canonicalPath + "!/")};
            URLClassLoader ucl = URLClassLoader.newInstance(urls, Thread.currentThread().getContextClassLoader());
            Thread.currentThread().setContextClassLoader(ucl);

            while (fileEntries.hasMoreElements()) {
                JarEntry currentFile = fileEntries.nextElement();

                if (currentFile.isDirectory()
                        || !currentFile.getName().endsWith(".class")) continue;

                String className = currentFile.getName()
                        .replace(".class", "")
                        .replace("/", ".");

                Class soletClazz = ucl.loadClass(className);

                this.loadIfSolet(soletClazz, applicationName);
            }
        } catch (MalformedURLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void loadClasses(String classFolderPath, String applicationName) {
        File classDirectory = new File(classFolderPath);

        if(!classDirectory.exists()) return;

        URL[] urls = new URL[0];

        try {
            urls = new URL[]{new File(classFolderPath).toURI().toURL()};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        URLClassLoader ucl = URLClassLoader.newInstance(urls, Thread.currentThread().getContextClassLoader());
        Thread.currentThread().setContextClassLoader(ucl);

        this.loadClass(classDirectory, "", ucl, applicationName);
    }

    private void loadLibraries(String libFolderPath, String applicationName) throws IOException {
        File libDirectory = new File(libFolderPath);

        if (libDirectory.exists() && libDirectory.isDirectory()) {
            for (File file : libDirectory.listFiles()) {
                if (!this.isJarFile(file)) {
                    continue;
                }

                JarFile library = new JarFile(file.getCanonicalPath());
                this.loadLibrary(library, file.getCanonicalPath(), applicationName);
            }
        }
    }

    public Map<String, Object> getSolets() {
        return Collections.unmodifiableMap(this.loadedSoletsByApplicationName);
    }

    public void loadApplications() {
        this.loadedSoletsByApplicationName = new HashMap<>();

        try {
            File appsDir = new File(APPLICATION_FOLDER_PATH);
            if (!appsDir.exists()) return;

            for (File file : appsDir.listFiles()) {
                if(this.isJarFile(file)) {
                    this.unzipJar(file);

                    String applicationName = file.getName().replace(".jar", "");

                    String libraryPath = file.getCanonicalPath().replace(".jar", "") + File.separator + "lib";
                    String classesPath = file.getCanonicalPath().replace(".jar", "") + File.separator + "classes";

                    this.loadLibraries(libraryPath, applicationName);
                    this.loadClasses(classesPath, applicationName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
