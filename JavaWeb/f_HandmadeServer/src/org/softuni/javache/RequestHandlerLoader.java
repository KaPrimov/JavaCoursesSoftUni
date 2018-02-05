package org.softuni.javache;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

public class RequestHandlerLoader {
	private static final String SERVER_FOLDER_PATH = "org/softuni/javache";
	private static final String LIB_DIRECTORY_PATH = RequestHandlerLoader.class.getResource("").getPath().replace(SERVER_FOLDER_PATH, "lib");
	
	public Set<RequestHandler> loadRequestHandlers() {
		
		Set<RequestHandler> requestHandlers = new HashSet<>();		
		this.loadFile(LIB_DIRECTORY_PATH.substring(1), requestHandlers);
		return requestHandlers;
	}
		

	private void loadFile(String path, Set<RequestHandler> requestHandlers) {

		File currentFileOrDir = new File(path);
		
		if (!currentFileOrDir.exists()) {
			return;
		}
		
		for (File fileOrDir : currentFileOrDir.listFiles()) {
			if (fileOrDir.isDirectory()) {
				this.loadFile(path + "/" + fileOrDir.getName(), requestHandlers);
			} else {
				try {
					URL fileUrl = fileOrDir.getParentFile().toURI().toURL();
					
					URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {fileUrl});
					
					Class<?> clazz = urlClassLoader.loadClass(fileOrDir.getName().replace(".class", ""));
					urlClassLoader.close();
					if (RequestHandler.class.isAssignableFrom(clazz)) {
						RequestHandler classInstance = (RequestHandler) clazz.getConstructor(String.class).newInstance(Server.class.getResource("").getPath());
						requestHandlers.add(classInstance);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
