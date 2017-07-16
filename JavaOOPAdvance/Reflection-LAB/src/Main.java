import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class reflection = Reflection.class;
//        System.out.println(reflection);
//        System.out.println(reflection.getSuperclass());
//
//        Class[] interfaces = reflection.getInterfaces();
//        for (Class anInterface : interfaces) {
//            System.out.println(anInterface);
//        }
//
//        Reflection refInstance = (Reflection)reflection.newInstance();
//        System.out.println(refInstance);

        Method[] methods = reflection.getDeclaredMethods();
        List<Method> getters = new ArrayList<>();
        List<Method> setters = new ArrayList<>();

        for (Method method : methods) {
            if(method.getName().startsWith("get")) {

                if(method.getParameterCount() == 0) {
                    getters.add(method);
                }

            } else if (method.getName().startsWith("set")) {
                setters.add(method);
            }
        }

        getters.sort(Comparator.comparing(Method::getName));
        setters.sort(Comparator.comparing(Method::getName));

        List<Field> fields = Arrays.asList(reflection.getDeclaredFields());
        fields.sort(Comparator.comparing(Field::getName));

        for (Field field : fields) {
            if(!Modifier.isPrivate(field.getModifiers())) {
                System.out.println(field.getName() + " must be private!");
            }
        }

        for (Method getter : getters) {
            if(!Modifier.isPublic(getter.getModifiers())) {
                System.out.println(getter.getName() + " have to be public!");
            }
        }

        for (Method setter : setters) {
            if(!Modifier.isPrivate(setter.getModifiers())) {
                System.out.println(setter.getName() + " have to be private!");
            }
        }
    }
}
