package org.softuni.javache;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class RequestHandlerLoader {
	private static final String SERVER_FOLDER_PATH = "org/softuni/javache";
	private static final String LIB_DIRECTORY_PATH = WebConstants.SERVER_ROOT_PATH + "lib";
	
	private Map<String, RequestHandler> requestHandlers;
	
	public Map<String, RequestHandler> loadRequestHandlers() {
		
		this.requestHandlers = new HashMap<>();		
		this.loadLibraries(LIB_DIRECTORY_PATH, requestHandlers);
		return requestHandlers;
	}	

	public Map<String, RequestHandler> getRequestHandlers() {
		return Collections.unmodifiableMap(requestHandlers);
	}

	private void loadLibraries(String libDirectoryPath, Map<String, RequestHandler> requestHandlers) {
		File libDirectory = new File(libDirectoryPath);
		
		if (libDirectory.exists() && libDirectory.isDirectory()) {
			for (File file : libDirectory.listFiles()) {
				if (!this.isLibraryFile(file)) {
					continue;
				}
				try {
					JarFile jarFile = new JarFile(file.getCanonicalPath());
					Enumeration<JarEntry> e = jarFile.entries();
	
					URL[] urls = { new URL("jar:file:" + file.getCanonicalPath() +"!/") };
					URLClassLoader cl = URLClassLoader.newInstance(urls);
	
					while (e.hasMoreElements()) {
					    JarEntry je = e.nextElement();
					    if(je.isDirectory() || !je.getName().endsWith(".class")){
					        continue;
					    }
					    
					    String className = je.getName().substring(0, je.getName().length() - 6);
					    className = className.replace('/', '.');
					    Class<?> handlerClass = cl.loadClass(className);
					    
					    if (RequestHandler.class.isAssignableFrom(handlerClass)) {
					    	RequestHandler requestHandler = (RequestHandler) handlerClass.getConstructor(String.class)
					    			.newInstance(WebConstants.SERVER_ROOT_PATH);
					    	
					    	requestHandlers.putIfAbsent(requestHandler.getClass().getSimpleName(), requestHandler);
					    }
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		
	}

	private boolean isLibraryFile(File file) {
		return file.getName().endsWith(".jar");
	}
}
