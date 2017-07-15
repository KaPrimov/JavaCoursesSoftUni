package kermen.models.home;

import kermen.models.Room;
import kermen.models.misc.Device;
import kermen.models.people.Child;
import kermen.models.people.Person;

import java.util.List;

public class YoungCoupleWithChildrenHome extends Home {

    public YoungCoupleWithChildrenHome(List<Person> people, List<Device> devices,  List<Child> children) {
        super();
        super.addPerson(people);
        super.addDevices(devices);
        super.addChildren(children);
        super.addRoom(new Room(30));
        super.addRoom(new Room(30));
    }
}
