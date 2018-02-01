package org.softuni.main.javache.http;

import java.util.Map;

public interface HttpSession {
	String getId();
	
	void addAttribute(String attribute, Object value);
	
	Map<String, Object> getAttributes();
	
	void invalidate();
}
