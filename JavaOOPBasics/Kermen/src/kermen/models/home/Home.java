package kermen.models.home;

import kermen.models.Room;
import kermen.models.misc.Device;
import kermen.models.people.Child;
import kermen.models.people.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class Home {
    private double totalMoney;
    private List<Person> homeOwners;
    private List<Room> rooms;
    private List<Child> children;
    private List<Device> devices;


    protected Home() {
        homeOwners = new ArrayList<>();
        rooms = new ArrayList<>();
        children = new ArrayList<>();
        devices = new ArrayList<>();
    }

    public double returnBills() {
        double totalBill = 0.0;

        for (Room room : rooms) {
            totalBill += room.getElectricityCost();
        }

        for (Child child : children) {
            totalBill += child.returnCosts();
        }

        for (Device device : devices) {
            totalBill += device.getCost();
        }

        return totalBill;
    }

    private double returnHomeSalary() {
        int totalPayment = 0;
        for (Person homeOwner : homeOwners) {
            totalPayment += homeOwner.getPayment();
        }

        return totalPayment;
    }

    public int returnPopulation() {
        return children.size() + homeOwners.size();
    }

    public boolean isBillPayed() {
        return this.totalMoney >= returnBills();
    }

    public void paySalary() {
        this.totalMoney += returnHomeSalary();
    }

    protected void addPerson(List<Person> people) {
        this.homeOwners.addAll(people);
    }

    protected void addRoom(Room room) {
        this.rooms.add(room);
    }

    protected void addChildren(List<Child> children) {
        this.children.addAll(children);
    }

    protected void addDevices(List<Device> devices) {
        this.devices.addAll(devices);
    }
}
