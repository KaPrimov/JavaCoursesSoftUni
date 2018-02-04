package com.KaPrim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToTheStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firstStarData = scanner.nextLine().split(" ");
        StarSystem starOne =
                new StarSystem(firstStarData[0], Double.parseDouble(firstStarData[1]), Double.parseDouble(firstStarData[2]));

        String[] secondStarData = scanner.nextLine().split(" ");
        StarSystem starTwo =
                new StarSystem(secondStarData[0], Double.parseDouble(secondStarData[1]), Double.parseDouble(secondStarData[2]));

        String[] thirdData = scanner.nextLine().split(" ");
        StarSystem starThree =
                new StarSystem(thirdData[0], Double.parseDouble(thirdData[1]), Double.parseDouble(thirdData[2]));

        List<StarSystem> stars = new ArrayList<>();
        stars.add(starOne);
        stars.add(starTwo);
        stars.add(starThree);

        String[] initialShipStart = scanner.nextLine().split(" ");
        double shipX = Double.parseDouble(initialShipStart[0]);
        double shipY = Double.parseDouble(initialShipStart[1]);
        int moves = scanner.nextInt();

        for (int move = 0; move <= moves ; move++) {
            boolean isInSpace = true;
            String systemName = "";
            for (int i = 0; i < 3 ; i++) {
                if (shipX <= stars.get(i).getLargerX() && shipX >= stars.get(i).getSmallerX()) {
                    if (shipY + move <= stars.get(i).getLargerY() && shipY + move >= stars.get(i).getSmallerY()) {
                        isInSpace = false;
                        systemName = stars.get(i).getName();
                    }
                }
            }
            if(isInSpace) {
                System.out.println("space");
            } else {
                System.out.println(systemName);
            }
        }

    }
    public static class StarSystem {
        private String name;
        private double sunX;
        private double sunY;

        public StarSystem(String name, double sunX, double sunY) {
            this.name = name;
            this.sunX = sunX;
            this.sunY = sunY;
        }

        public double getSmallerX() {
            return this.sunX-1;
        }

        public double getLargerX() {
            return this.sunX+1;
        }

        public double getSmallerY() {
            return this.sunY-1;
        }

        public double getLargerY() {
            return this.sunY+1;
        }

        public String getName() {
            return name.toLowerCase();
        }
    }
}
