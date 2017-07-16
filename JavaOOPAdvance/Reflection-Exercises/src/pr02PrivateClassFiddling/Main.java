package pr02PrivateClassFiddling;

import pr02PrivateClassFiddling.com.peshoslav.BlackBoxInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, NoSuchFieldException {

	    Class<BlackBoxInt> reflection = (Class<BlackBoxInt>) Class.forName("pr02PrivateClassFiddling.com.peshoslav.BlackBoxInt");
        Constructor<BlackBoxInt> constructor = reflection.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInstance = constructor.newInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] params = reader.readLine().split("_");
            if (params[0].equals("END")){
                break;
            }

            String methodName = params[0];
            int value = Integer.parseInt(params[1]);

            Method method = reflection.getDeclaredMethod(methodName, int.class);
            method.setAccessible(true);
            method.invoke(blackBoxInstance, value);

            Field field = reflection.getDeclaredField("innerValue");
            field.setAccessible(true);
            System.out.println(field.get(blackBoxInstance));
        }
	}
}
