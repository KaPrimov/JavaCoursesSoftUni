package database;

import javax.naming.OperationNotSupportedException;

public class Main {

    public static void main(String[] args) throws OperationNotSupportedException {
        Person[] arr = new Person[16];
        for (int i = 0; i < 15; i++) {
            arr[i] = new Person(i, "Pesho" + i);
        }

        IDatabase<Person> database = new Database<Person>(arr);
        database.remove();
        database.add(new Person(123, "Testing"));

        System.out.println(database.fetch());
    }

}
