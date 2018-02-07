package org.softuni.javache;

import java.util.LinkedList;
import java.util.List;

public class ServerConfig {
	
	private List<String> handlers;
	
	public ServerConfig() {
		this.initializeConfig();
	}

	private void initializeConfig() {
		this.handlers = new LinkedList<>();		
	}

	public List<String> getHandlers() {
		return handlers;
	}	
	
}
