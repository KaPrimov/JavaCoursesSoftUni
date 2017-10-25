package organization;

import java.util.*;

public class Organization implements IOrganization {

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean contains(Person person) {
        return false;
    }

    @Override
    public boolean containsByName(String name) {
        return false;
    }

    @Override
    public void add(Person person) {

    }

    @Override
    public Person getAtIndex(int index) {
        return null;
    }

    @Override
    public Iterable<Person> getByName(String name) {
        return null;
    }

    @Override
    public Iterable<Person> firstByInsertOrder() {
        return null;
    }

    @Override
    public Iterable<Person> firstByInsertOrder(int count) {
        return null;
    }

    @Override
    public Iterable<Person> searchWithNameSize(int minLength, int maxLength) {
        return null;
    }

    @Override
    public Iterable<Person> getWithNameSize(int length) {
        return null;
    }

    @Override
    public Iterable<Person> peopleByInsertOrder() {
        return null;
    }

    @Override
    public Iterator<Person> iterator() {
        return null;
    }
}
