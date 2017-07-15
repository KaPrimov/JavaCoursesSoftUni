package kermen.models.home;

import kermen.models.Room;
import kermen.models.people.Person;

import java.util.List;

public class AloneOldHome extends Home {

    public AloneOldHome(List<Person> people) {
        super();
        super.addPerson(people);
        super.addRoom(new Room(15));
    }
}
