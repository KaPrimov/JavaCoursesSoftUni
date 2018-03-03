package org.softuni.javache.util;

import org.softuni.javache.WebConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ServerConfig {
    private static final String CONFIG_FILE_NAME = "config.ini";

    private static final String CONFIG_FILE_FULL_PATH =
            WebConstants
            .WEB_SERVER_ROOT_FOLDER_PATH
            + CONFIG_FILE_NAME;

    private LinkedHashSet<String> requestHandlersPriority;

    public ServerConfig() {
        this.requestHandlersPriority = new LinkedHashSet<>();
    }

    private void loadRequestHandlerConfig(String configFileLine) {
        List<String> requestHandlerConfigParams =
                Arrays.asList(configFileLine
                .split("[\\s\\,]"))
                .stream()
                .filter(x -> x.length() > 0)
                .collect(Collectors.toList());

        for (int i = 1; i < requestHandlerConfigParams.size(); i++) {
            this.requestHandlersPriority.add(requestHandlerConfigParams.get(i));
        }
    }

    public Set<String> getRequestHandlersPriority() {
        return Collections.unmodifiableSet(this.requestHandlersPriority);
    }

    public void loadConfig() {
        try {
            List<String> configFileContents
                    = Files.readAllLines(Paths.get(CONFIG_FILE_FULL_PATH));

            for (String configFileLine : configFileContents) {
                if(configFileLine.startsWith("request-handlers")) {
                    this.loadRequestHandlerConfig(configFileLine);
                }
            }
        } catch (IOException e) {
            ;
        }
    }
}
