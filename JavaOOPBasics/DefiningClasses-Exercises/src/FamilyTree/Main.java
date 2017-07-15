package FamilyTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        Person searchedPerson = null;

        if(line.contains("/")) {
            searchedPerson = new Person(line);
        } else {
            String[] tokens = line.split(" ");
            String firstName = tokens[0];
            String lastName = tokens[1];
            searchedPerson = new Person(firstName, lastName);
        }

        List<Person> fullPeople = new ArrayList<>();
        List<Person> halfPeople = new ArrayList<>();

        while (!(line = reader.readLine()).equals("End")) {
            String[] tokens = line.split(" - ");
            switch (tokens.length) {
                case 1:
                    String[] fullInfo = tokens[0].split(" ");
                    Person person = new Person(fullInfo[0], fullInfo[1], fullInfo[2]);
                    fullPeople.add(person);
                    break;
                case 2:
                    String parentInfo = tokens[0];
                    String childInfo = tokens[1];
                    Person parent = null;
                    Person child = null;
                    if(parentInfo.contains("/")) {
                        parent = new Person(parentInfo);
                    } else {
                        String[] names = parentInfo.split(" ");
                        parent = new Person(names[0], names[1]);
                    }

                    if(childInfo.contains("/")) {
                        child = new Person(childInfo);
                    } else {
                        String[] names = childInfo.split(" ");
                        child = new Person(names[0], names[1]);
                    }

                    parent.addChild(child);
                    child.addParent(parent);
                    halfPeople.add(parent);
                    halfPeople.add(child);
            }
        }
        halfPeople.forEach(partial -> {
            fullPeople
                .stream()
                .filter(
                        person -> person.getName().equals(partial.getName()) ||
                                person.getDate().equals(partial.getDate())
                )
                .forEach(fullPerson -> {
                    partial.getChildren().forEach(fullPerson::addChild);
                    partial.getParents().forEach(fullPerson::addParent);
                    partial.setName(fullPerson.getName());
                    partial.setDate(fullPerson.getDate());
                });
        });

        for (Person fullPerson : fullPeople) {
            if(fullPerson.getName().equals(searchedPerson.getName()) ||
                    fullPerson.getDate().equals(searchedPerson.getDate())) {
                System.out.println(fullPerson);
            }
        }
    }
}
