package exam.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageWriter {
    private static MessageWriter INSTANCE;

    private MessageWriter() {    }

    public static MessageWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MessageWriter();
        }
        return INSTANCE;
    }

    public void printSuccess(Class classInstance, String name) {
        System.out.printf("Successfully imported %s %s." + System.lineSeparator(),
                findName(classInstance.getSimpleName()), name);
    }

    private String findName(String name) {
        Pattern pattern = Pattern.compile("[A-Z][a-z]*");
        Matcher matcher = pattern.matcher(name);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group());
            sb.append(" ");
        }
        return sb.delete(sb.length() - 1, sb.length()).toString();
    }

    public void printError() {
        System.out.println("Error: Invalid data.");
    }
}
