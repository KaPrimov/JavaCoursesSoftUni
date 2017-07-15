package google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Person> people = new HashMap<>();
        while (true){
            String input = reader.readLine();
            if (input.equals("End")){
                break;
            }
            String[] inputParams = input.split("\\s+");
            String name = inputParams[0];
            if (!people.containsKey(name)){
                people.put(name, new Person(name));
            }

            Person person = people.get(name);
            String command = inputParams[1];

            switch (command){
                case "company":
                    addCompany(person, inputParams);
                    break;

                case "pokemon":
                    addPokemon(person, inputParams);
                    break;

                case "parents":
                    addParent(person, inputParams);
                    break;

                case "children":
                    addChild(person, inputParams);
                    break;

                case "car":
                    addCar(person, inputParams);
                    break;
            }
        }

        String toSearch = reader.readLine();
        System.out.println(people.get(toSearch));
    }

    private static void addCar(Person person, String[] params) {
        String model = params[2];
        int speed = Integer.valueOf(params[3]);
        Car car = new Car(model, speed);
        person.setCar(car);
    }

    private static void addChild(Person person, String[] params) {
        String childName = params[2];
        String birthday = params[3];
        Child child = new Child(childName, birthday);
        person.addChild(child);
    }

    private static void addParent(Person person, String[] params) {
        String parentName = params[2];
        String birthday = params[3];
        Parent parent = new Parent(parentName, birthday);
        person.addParent(parent);
    }

    private static void addPokemon(Person person, String[] params) {
        String name = params[2];
        String type = params[3];
        Pokemon pokemon = new Pokemon(name, type);
        person.addPokemon(pokemon);
    }

    private static void addCompany(Person person, String[] params) {
        String companyName = params[2];
        String department = params[3];
        double salary = Double.valueOf(params[4]);
        Company company = new Company(companyName, department, salary);
        person.setCompany(company);
    }
}
