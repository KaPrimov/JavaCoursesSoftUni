package org.softuni.main.casebook.utils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.softuni.main.casebook.annotations.Get;
import org.softuni.main.casebook.annotations.Post;

public class HandlerLoader {

	private static final String DYNAMIC_HANDLERS_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\org\\softuni\\main\\casebook\\handlers\\dynamicHandlers";
	private static final String DYNAMIC_HANDLERS_PACKAGE = "org.softuni.main.casebook.handlers.dynamicHandlers";
	
	private Map<String, Map<String, Method>> routingActions;
		
	public HandlerLoader() {
		this.loadMaps();
	}

	private void loadMaps() {
		this.routingActions = new HashMap<>();
		File directory = new File(DYNAMIC_HANDLERS_PATH);
		
		this.routingActions.put("GET", new HashMap<>());
		this.routingActions.put("POST", new HashMap<>());
	
		List<Class<?>> classes = Arrays.asList(directory.listFiles())
				.stream()
				.map(h -> {
					try {
						String fileFullName = String.valueOf(h);
						String className = fileFullName.substring(fileFullName.lastIndexOf("\\") + 1).replace(".java", "");
						Class<?> foundClass = Class.forName(DYNAMIC_HANDLERS_PACKAGE + "." + className);
						
						return foundClass;
						
					} catch (ClassNotFoundException cnfe) {
						System.out.println("cnfe");
						return null;
					}
				})
				.filter(x -> x != null)
				.collect(Collectors.toList());
		
		for (Class<?> loadedClass : classes) {
			for (Method method : loadedClass.getDeclaredMethods()) {
				method.setAccessible(true);
				
				for(Annotation annotation : method.getDeclaredAnnotations()) {
					if (annotation.annotationType().getSimpleName().equals("Get")) {
						this.routingActions.get("GET").put(((Get) annotation).route(), method);
					} else if (annotation.annotationType().getSimpleName().equals("Post")) {
						this.routingActions.get("POST").put(((Post) annotation).route(), method);
					}
				}
			}
		}
	}
	
	public Map<String, Method> retrieveActions(String method) {
		if (!this.routingActions.containsKey(method)) {
			return null;
		}
		return Collections.unmodifiableMap(this.routingActions.get(method));
	}
}
