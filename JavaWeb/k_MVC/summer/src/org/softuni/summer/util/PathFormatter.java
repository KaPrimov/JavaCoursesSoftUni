package org.softuni.summer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PathFormatter {
    private static final String PATH_VARIABLE_PATTERN = "(?<group-name>[a-zA-Z0-9\\-\\_]+)";

    private static final String PARAMETER_PATTERN = "(\\{([a-zA-Z][a-zA-Z0-9]*)\\})";

    public String formatPattern(String mappingRoute) {
        Matcher matcher = Pattern
                .compile(PARAMETER_PATTERN)
                .matcher(mappingRoute);

        while(matcher.find()) {
            String parameterValue = matcher.group(1);
            String parameter = matcher.group(2);

            String newValue = PATH_VARIABLE_PATTERN.replace("group-name", parameter);

            mappingRoute = mappingRoute.replace(parameterValue, newValue);
        }

        return "^" + mappingRoute + "$";
    }
}
