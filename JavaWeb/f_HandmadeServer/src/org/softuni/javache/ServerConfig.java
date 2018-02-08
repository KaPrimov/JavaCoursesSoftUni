package org.softuni.javache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ServerConfig {
	
	private static final String REQUEST_HANDLERS_LABEL = "request-handlers:";
	private static final String CONFIG_FILE_NAME = "config.ini";
	private static final String CONFIG_FULL_PATH = WebConstants.SERVER_ROOT_PATH + CONFIG_FILE_NAME;
	
	
	private Set<String> handlers;
	
	public ServerConfig() {
		this.handlers = new LinkedHashSet<>();
	}

	public void initializeConfig() {
		this.handlers = new LinkedHashSet<>();
		try {
			List<String> configFileContents = Files.readAllLines(Paths.get(CONFIG_FULL_PATH));
			for (String configLine : configFileContents) {
				if (configLine.startsWith(REQUEST_HANDLERS_LABEL)) {
					this.loadRequestHandlersConfig(configLine);
				}
			}
		} catch (IOException e) {
			;
		}
	}

	private void loadRequestHandlersConfig(String configLine) {
		String[] tokens = configLine.replace(REQUEST_HANDLERS_LABEL, "").trim().split(",\\s+");
		for (String handlerName : tokens) {
			this.handlers.add(handlerName);
		}
	}

	public Set<String> getHandlers() {
		return Collections.unmodifiableSet(handlers);
	}	
	
}
