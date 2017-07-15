package strategyPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Set<Person> namePeople = new TreeSet<>(new NameComparator());
        Set<Person> agePeople = new TreeSet<>(new AgeComparator());

        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]));
            namePeople.add(person);
            agePeople.add(person);
        }

        for (Person person : namePeople) {
            System.out.println(person);
        }

        for (Person person : agePeople) {
            System.out.println(person);
        }
    }
}
