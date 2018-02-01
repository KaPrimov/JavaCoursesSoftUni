package org.softuni.main.javache.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpSessionImpl implements HttpSession {
	
	private String id;
	private Map<String, Object> attributes;
	
	public HttpSessionImpl(String id) {
		this.id = id;
		this.attributes = new HashMap<>();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void addAttribute(String attribute, Object value) {
		this.attributes.putIfAbsent(attribute, value);

	}

	@Override
	public Map<String, Object> getAttributes() {
		return Collections.unmodifiableMap(this.attributes);
	}

	@Override
	public void invalidate() {
		this.attributes.clear();
	}

}
