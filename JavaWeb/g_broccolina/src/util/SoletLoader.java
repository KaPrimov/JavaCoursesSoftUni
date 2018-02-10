package util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import solet.HttpSolet;
import solet.WebSolet;

public class SoletLoader {
	public final String APPLICATION_FOLDER_PATH;

	private Map<String, HttpSolet> loadedSoletsByApplicationName;
	
	public SoletLoader(String serverRootPath) {
		this.APPLICATION_FOLDER_PATH = serverRootPath + "apps";
	}
	
	public Map<String, HttpSolet> getSolets() {
		return Collections.unmodifiableMap(this.loadedSoletsByApplicationName);
	}
	
	public void loadSolets() {
		this.loadedSoletsByApplicationName = new HashMap<>();
		File appDir = new File(this.APPLICATION_FOLDER_PATH);
		
		if (!appDir.exists()) return;
		
		for (File file : appDir.listFiles()) {
			try {
				this.loadLibraries(file.getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void loadLibrary(JarFile jarFile, String canonicalPath) {
		try {
			Enumeration<JarEntry> e = jarFile.entries();

			URL[] urls = { new URL("jar:file:" + canonicalPath +"!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
			    JarEntry je = e.nextElement();
			    if(je.isDirectory() || !je.getName().endsWith(".class")){
			        continue;
			    }
			    
			    String className = je.getName().substring(0, je.getName().length() - 6);
			    className = className.replace('/', '.');
			    Class<?> handlerClass = cl.loadClass(className);
			    
			    if (HttpSolet.class.isAssignableFrom(handlerClass)) {
			    	HttpSolet solet = (HttpSolet) handlerClass.getConstructor()
			    			.newInstance();
			    	
			    	String appPath = new File(canonicalPath).getParent();
			    	
			    	this.loadedSoletsByApplicationName.putIfAbsent(
			    			appPath.substring(appPath.lastIndexOf("/") + 1) + solet.getClass().getDeclaredAnnotation(WebSolet.class).route(), 
			    			solet);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
		
	
	
	private void loadLibraries(String libFolderPath) throws IOException {
		File libDir = new File(libFolderPath);
		if (libDir.exists() && libDir.isDirectory()) {
			for (File file : libDir.listFiles()) {	
				if (file.isDirectory()) {
					this.loadLibraries(file.getCanonicalPath());
				} else if (!this.isLibraryFile(file)) {
					continue;
				}
				JarFile library = new JarFile(file.getCanonicalPath());
				loadLibrary(library, file.getCanonicalPath());
			}
				
		}
		
	}

	private boolean isLibraryFile(File file) {
		return file.getName().endsWith(".jar");
	}
	
}
