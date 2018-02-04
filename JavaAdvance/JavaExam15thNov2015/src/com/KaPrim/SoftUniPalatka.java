package com.KaPrim;

import java.io.IOException;
import java.util.Scanner;

public class SoftUniPalatka {

    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);

        int availableMeals = 0;
        int availableBeds = 0;

        int people = Integer.parseInt(scn.nextLine().trim());
        int N = Integer.parseInt(scn.nextLine().trim());

        for (int i = 1; i <= N; i++) {
            String[] lineArgs = scn.nextLine().trim().split("\\s+");
            switch (lineArgs[0]) {
                case "rooms":
                    String roomType = lineArgs[2];
                    int numberOfRooms = Integer.parseInt(lineArgs[1]);
                    if (roomType.equals("single"))
                        availableBeds += numberOfRooms;
                    if (roomType.equals("double"))
                        availableBeds += numberOfRooms * 2;
                    if (roomType.equals("triple"))
                        availableBeds += numberOfRooms * 3;
                    break;
                case "tents":
                    String tentType = lineArgs[2];
                    int numberOfTents = Integer.parseInt(lineArgs[1]);
                    if (tentType.equals("firstClass"))
                        availableBeds += numberOfTents * 3;
                    if (tentType.equals("normal"))
                        availableBeds += numberOfTents * 2;
                    break;
                case "food":
                default:
                    String mealType = lineArgs[2];
                    int numberOfMeals = Integer.parseInt(lineArgs[1]);
                    if (mealType.equals("musaka"))
                        availableMeals += numberOfMeals * 2;
                    break;
            }
        }

        if (availableBeds >= people) {
            System.out.println("Everyone is happy and sleeping well. Beds left: " + (availableBeds - people));
        } else {
            System.out.println("Some people are freezing cold. Beds needed: " + (people - availableBeds));
        }
        if (availableMeals >= people) {
            System.out.println("Nobody left hungry. Meals left: " + (availableMeals - people));
        } else {
            System.out.println("People are starving. Meals needed: " + (people - availableMeals));
        }
    }
}
