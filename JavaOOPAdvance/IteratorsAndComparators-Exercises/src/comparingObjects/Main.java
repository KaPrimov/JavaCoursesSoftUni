package comparingObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> people = new LinkedList<>();
        int equal = 0;
        int different = 0;

        while (true) {
            String[] tokens = reader.readLine().split("\\s+");
            if("END".equals(tokens[0])) {
                break;
            }

            people.add(new Person(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
        }

        int index = Integer.parseInt(reader.readLine());

        Person personToCompare = people.remove(index-1);

        for (Person person : people) {
            if (person.compareTo(personToCompare) == 0) {
                equal++;
            } else {
                different++;
            }
        }

        if (equal == 0) {
            System.out.println("No matches");
        } else {
            System.out.printf("%d %d %d", equal+1, different, people.size()+1);
        }
    }
}
