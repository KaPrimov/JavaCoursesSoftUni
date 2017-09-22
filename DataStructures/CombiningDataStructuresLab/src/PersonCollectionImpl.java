import java.util.*;

public class PersonCollectionImpl implements PersonCollection {

    private Map<String, Person> users;
    private Map<String, SortedSet<Person>> domainUsers;
    private Map<String, SortedSet<Person>> nameTownUsers;
    private SortedMap<Integer, SortedSet<Person>> ageUsers;
    private SortedMap<String, SortedSet<Person>> ageTownUsers;

    public PersonCollectionImpl() {
        this.users = new HashMap<>();
        this.domainUsers = new HashMap<>();
        this.nameTownUsers = new HashMap<>();
        this.ageUsers = new TreeMap<>();
        this.ageTownUsers = new TreeMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if(users.containsKey(email)) {
            return false;
        }

        Person person = new Person(email, name, age, town);
        String domain = email.substring(email.lastIndexOf('@') + 1);

        users.put(email, person);

        domainUsers.putIfAbsent(domain, new TreeSet<>());
        domainUsers.get(domain).add(person);

        ageUsers.putIfAbsent(age, new TreeSet<>());
        ageUsers.get(age).add(person);

        ageTownUsers.putIfAbsent(age + town, new TreeSet<>());
        ageTownUsers.get(age + town).add(person);

        nameTownUsers.putIfAbsent(town, new TreeSet<>());
        nameTownUsers.get(town).add(person);

        return true;
    }

    @Override
    public int getCount() {
        return this.users.size();
    }

    @Override
    public Person findPerson(String email) {
        return users.get(email);
    }

    @Override
    public boolean deletePerson(String email) {

        Person person = this.users.remove(email);
        return person != null;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        return this.domainUsers.get(emailDomain) == null ? new ArrayList<>() : this.domainUsers.get(emailDomain);
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        List<Person> people = new ArrayList<>();
        if (this.nameTownUsers.get(town) == null) {
            return people;
        }
        for (Person person : this.nameTownUsers.get(town)) {
            if(person.getName().equals(name)) {
                people.add(person);
            }
        }
        return people;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        List<Person> people = new ArrayList<>();
        for (SortedSet<Person> personSet : this.ageUsers.subMap(startAge, endAge + 1).values()) {
            people.addAll(personSet);
        }
        return people;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        List<Person> people = new ArrayList<>();
        for (SortedSet<Person> personSet : this.ageUsers.subMap(startAge, endAge + 1).values()) {
            for (Person person : personSet) {
                if(person.getTown().equals(town)) {
                    people.add(person);
                }
            }
        }
        return people;
    }
}
