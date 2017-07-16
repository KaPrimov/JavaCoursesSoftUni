package database;

import javax.naming.OperationNotSupportedException;

public class Database<T extends Person> implements IDatabase<T> {

    private T[] people;
    private int index;

    public Database(T... people) throws OperationNotSupportedException {
        this.setPeople(people);
    }


    public void add(T number) throws OperationNotSupportedException {
        if(this.index == 15) {
            throw new OperationNotSupportedException();
        }
        for (int i = 0; i < this.people.length; i++) {
            if(people[i] == null) {
                this.people[i] = number;
                index++;
                return;
            }
        }
        throw new OperationNotSupportedException();
    }

    public void remove() {
        this.people[--this.index] = null;
        this.index--;
    }

    public T[] fetch() {
        return this.people;
    }

    private void setPeople(T[] people) throws OperationNotSupportedException {
        if(people.length > 16 || people.length < 1) {
            throw new OperationNotSupportedException();
        }
        this.people =(T[]) new Person[16];
        this.index = people.length;
        for (int i = 0; i < people.length; i++) {
            this.people[i] = people[i];
        }
    }

    public int getIndex() {
        return index;
    }

    public T findById(long id) {
        for (T person : people) {
            if(person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public T findByUsername(String username) {
        for (T person : people) {
            if(person.getUsername().equalsIgnoreCase(username)) {
                return person;
            }
        }
        return null;
    }
}
