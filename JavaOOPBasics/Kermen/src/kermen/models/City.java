package kermen.models;

import kermen.models.home.Home;

import java.util.LinkedList;
import java.util.List;

public class City {
    private List<Home> homes;

    public City() {
        homes = new LinkedList<>();
    }

    public void payBills() {
        for (Home home : homes) {
            if(!home.isBillPayed()) {
                homes.remove(home);
            }
        }
    }

    public double returnConsumption() {
        double totalConsumption = 0.0;
        for (Home home : homes) {
            totalConsumption += home.returnBills();
        }

        return totalConsumption;
    }

    public void addHome(Home home) {
        this.homes.add(home);
    }

    public int returnPopulation() {
        return this.homes.stream().mapToInt(Home::returnPopulation).sum();
    }

    public void paySalaries() {
        for (Home home : homes) {
            home.paySalary();
        }
    }
}
