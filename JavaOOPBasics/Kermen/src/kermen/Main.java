package kermen;

import kermen.models.City;
import kermen.models.HomeFactory;
import kermen.models.home.Home;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HomeFactory homeFactory = new HomeFactory();
        City city = new City();
        int count = 1;

        label:
        while (true) {
            if(count%3 == 0) {
                city.paySalaries();
            }
            String line = reader.readLine();
            switch (line) {
                case "EVN bill":
                    city.payBills();
                    break;
                case "EVN":
                    System.out.println(String.format("Total consumption: %.1f", city.returnConsumption()));
                    break;
                case "Democracy":
                    break label;
                default:
                    Home home = homeFactory.homeFactory(line);
                    city.addHome(home);
                    break;
            }

            count++;
        }
        System.out.println("Total population: " + city.returnPopulation());

    }
}
