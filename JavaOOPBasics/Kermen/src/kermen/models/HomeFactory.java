package kermen.models;

import kermen.models.home.*;
import kermen.models.misc.Device;
import kermen.models.people.Child;
import kermen.models.people.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFactory {

    public Home homeFactory(String input) {
        Home home = null;
        String[] tokens = input.split("(?<=\\))\\s(?=[A-Z])");
        String command = tokens[0].split("\\(")[0];
        List<Person> people = null;
        List<Device> devices = null;
        List<Child> children= null;

        switch (command) {
            case "YoungCouple":
                people = returnPerson(tokens[0]);
                devices = returnDevices(tokens[1], tokens[2], tokens[3], tokens[3]);
                home = new YoungCoupleHome(people, devices);
                break;
            case "YoungCoupleWithChildren":
                people = returnPerson(tokens[0]);
                devices = returnDevices(tokens[1], tokens[2], tokens[3], tokens[3]);
                String[] childrenCosts = Arrays.copyOfRange(tokens, 4, tokens.length );
                children = returnChildren(childrenCosts);
                home = new YoungCoupleWithChildrenHome(people, devices, children);
                break;
            case "AloneYoung":
                people = returnPerson(tokens[0]);
                devices = returnDevices(tokens[1]);
                home = new AloneYoungHome(people, devices);
                break;
            case "OldCouple":
                people = returnPerson(tokens[0]);
                devices = returnDevices(tokens[1], tokens[2], tokens[3]);
                home = new OldCoupleHome(people, devices);
                break;
            case "AloneOld":
                people = returnPerson(tokens[0]);
                home = new AloneOldHome(people);
                break;
        }

        return home;
    }

    private List<Child> returnChildren(String[] childrenCosts) {
        List<Child> list = new ArrayList<>();

        for (String costs : childrenCosts) {
            String[] amenities = costs.substring(6, costs.length()-1).split(", ");
            List<Device> listAmenities = new ArrayList<>();
            for (String amenity : amenities) {
                listAmenities.add(new Device(Double.parseDouble(amenity)));
            }
            list.add(new Child(listAmenities));
        }
        return list;
    }

    private List<Device> returnDevices(String...devicesInput) {
        List<Device> list = new ArrayList<>();
        for (String device : devicesInput) {
            String data = device.substring(device.indexOf("(")+1, device.indexOf(")"));
            Device deviceToAdd = new Device(Double.parseDouble(data));
            list.add(deviceToAdd);
        }
        return list;
    }

    private List<Person> returnPerson(String input) {
       String[] data = input.substring(input.indexOf("(") + 1, input.indexOf(")")).split(", ");
       List<Person> people = new ArrayList<>();

       for (String wage : data) {
           Person person = new Person(Integer.parseInt(wage));
           people.add(person);
       }

       return people;
   }
}
