package codingTracker;

import java.lang.reflect.Method;
import java.util.*;

public class Tracker {

    @Author(name = "Pesho")
    public static void printMethodsByAuthor() {
        Map<String, List<String>> methodsByAuthor = new HashMap<>();
        Method[] methods = Tracker.class.getDeclaredMethods();
        for (Method method : methods) {
            Author annotation = method.getAnnotation(Author.class);
            if (annotation != null) {
                methodsByAuthor.putIfAbsent(annotation.name(), new ArrayList<>());
                methodsByAuthor.get(annotation.name()).add(method.getName() + "()");
            }
        }

        for (String s : methodsByAuthor.keySet()) {
            System.out.printf("%s: %s", s, String.join(", ", methodsByAuthor.get(s)));
        }
    }
}
