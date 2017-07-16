package equalityLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        TreeSet<Person> namePeople = new TreeSet<>();
        HashSet<Person> agePeople = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]));
            namePeople.add(person);
            agePeople.add(person);
        }

        System.out.println(namePeople.size());
        System.out.println(agePeople.size());
    }
}
