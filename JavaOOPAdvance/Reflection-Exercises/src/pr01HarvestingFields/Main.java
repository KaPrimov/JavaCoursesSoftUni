package pr01HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {
	public static void main(String[] args) throws IOException {
		Class reflection = RichSoilLand.class;
        Field[] fields = reflection.getDeclaredFields();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = reader.readLine();
            if("HARVEST".equals(line)) {
                break;
            }

            for (Field field : fields) {

                int modifierInt = field.getModifiers();
                String modifier = Modifier.toString(modifierInt);
                if(!modifier.equals(line) && !line.equals("all")) {
                    continue;
                }
                System.out.println(String.format("%s %s %s", modifier, field.getType().getSimpleName(), field.getName()));
            }

        }
	}
}
