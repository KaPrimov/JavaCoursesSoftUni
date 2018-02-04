package com.KaPrim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Vehicle_Park {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vehiclesSold = 0;
        String[] firstLine = scanner.nextLine().split(" ");
        List<String> carPark = new ArrayList<>();
        for (int i=0; i<firstLine.length; i++) {
            carPark.add(firstLine[i]);
        }
        while (true) {
            String command = scanner.nextLine();
            if(command.equals("End of customers!")) {
                break;
            }
            String[] order = command.split(" ");
            String typeOfVehicle = order[0].substring(0, 1).toLowerCase();
            String seats = order[2];
            String searchedVehicle = typeOfVehicle + seats;
            int index = carPark.indexOf(searchedVehicle);
            if (index != -1) {
                int letter = searchedVehicle.charAt(0);
                int seatsVal = Integer.parseInt(searchedVehicle.substring(1));
                long totalPrice = letter * seatsVal;
                System.out.printf("Yes, sold for %d$\n", totalPrice);
                vehiclesSold++;
                carPark.remove(searchedVehicle);
            } else {
                System.out.println("No");
            }
        }
        System.out.println("Vehicles left:" + Arrays.toString(carPark.toArray()).replace('[', ' ').replace(']', ' '));
        System.out.println("Vehicles sold: " + vehiclesSold);
    }
}
