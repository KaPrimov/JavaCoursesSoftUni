package CatLady;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Cat> cats = new ArrayList<>();

        while (true) {
            String[] line = reader.readLine().split(" ");
            if("End".equals(line[0])) {
                break;
            }
            Cat cat = null;
            switch (line[0]) {
                case "StreetExtraordinaire":
                    cat = new StreetExtraordinaire(line[1], line[2]);
                    break;
                case "Siamese":
                    cat = new Siamese(line[1], line[2]);
                    break;
                case "Cymric":
                    cat = new Cymric(line[1], line[2]);
                    break;
            }

            cats.add(cat);
        }

        String searchedName = reader.readLine();

        for (Cat cat : cats) {
            if(cat.getName().equals(searchedName)) {
                System.out.println(cat.toString());
            }
        }
    }
}
