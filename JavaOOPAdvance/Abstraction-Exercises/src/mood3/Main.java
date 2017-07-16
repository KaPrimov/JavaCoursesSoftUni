package mood3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split(" \\| ");
        String name = tokens[0];
        String type = tokens[1];
        String specailPoints = tokens[2];
        int level = Integer.parseInt(tokens[3]);

        IGameObject iGameObject = null;

        if(type.contains("Demon")) {
            iGameObject = new Demon(name, level);
        } else if (type.contains("Archangel") ) {
            iGameObject = new Angel(name, level);
        }
        System.out.println(iGameObject.toString());
        if (type.equals("Demon")) {
            System.out.println(Double.parseDouble(specailPoints) * level);

        } else {
            System.out.println(Integer.parseInt(specailPoints) * level);
        }
    }
}
