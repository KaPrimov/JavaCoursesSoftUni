package kermen.models.home;

import kermen.models.Room;
import kermen.models.misc.Device;
import kermen.models.people.Person;

import java.util.List;

public class AloneYoungHome extends Home {

    public AloneYoungHome(List<Person> people, List<Device> devices) {
        super();
        super.addPerson(people);
        super.addDevices(devices);
        super.addRoom(new Room(10));

    }
}
