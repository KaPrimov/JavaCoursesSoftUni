package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int maxCapacity;
    public static void main(String[] args) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	    maxCapacity = Integer.parseInt(reader.readLine());

        List<Bunker> bunkers = new LinkedList<>();
        String[] input = reader.readLine().split(" ");
        while (true) {
            List<Integer> weaponInput = new LinkedList<>();
            if ((input[0] + input[1]).equals("BunkerRevision")) {
                break;
            }

            for (String s : input) {
                if (isNumeric(s)) {
                    if (Integer.parseInt(s) <= maxCapacity) {
                        weaponInput.add(Integer.parseInt(s));
                    }
                } else {
                    Bunker bunker = new Bunker(s);
                    bunkers.add(bunker);
                }
            }
            int currentIndex = 0;
            for (int j = 0; j < bunkers.size(); j++) {
                while (!weaponInput.isEmpty() && !bunkers.get(j).isOverflow){
                    if(weaponInput.size() == 1) {
                        currentIndex = 0;
                    }
                    boolean isAdded = bunkers.get(j).addWeapon(weaponInput.get(currentIndex));
                    System.out.println(j);
                    if (!isAdded) {
                        currentIndex++;
                        if (bunkers.size() == 1) {
                            bunkers.get(j).lastAddOfWeapon(weaponInput.get(currentIndex));
                            weaponInput.remove(0);
                        }
                    } else {
                        weaponInput.remove(currentIndex);
                        currentIndex = 0;
                    }
                }
            }

            input = reader.readLine().split(" ");
        }

        for (Bunker bunker : bunkers) {
            System.out.println(bunker);
        }
    }

    private static boolean isNumeric(String str)
    {
        return str.matches("\\d+");  //match a number with optional '-' and decimal.
    }

    private static class Bunker {

        private String name;
        private int capacity;
        private List<Integer> weapons;
        private boolean isOverflow;

        private Bunker(String name) {
            this.name = name;
            this.capacity = maxCapacity;
            this.weapons = new LinkedList<>();
            this.isOverflow = false;
        }

        private boolean addWeapon(int weapon) {
            if (weapon > capacity) {
                return false;
            }
            weapons.add(weapon);
            capacity -= weapon;
            if (capacity == 0) {
                isOverflow = true;
            }
            return true;
        }

        public void lastAddOfWeapon(int weapon) {
            for (int i = weapons.size()-1; i >= 0; i--) {
                weapons.remove(i);
                if(canBeAdded(weapons, weapon)) {
                    weapons.add(weapon);
                    break;
                }
            }
        }

        private boolean canBeAdded(List<Integer> weapons, int weapon) {
            int sum = weapons.stream().mapToInt(Integer::valueOf).sum();
            return sum + weapon <= maxCapacity;
        }


        public String getName() {
            return name;
        }

        public int getCapacity() {
            return capacity;
        }

        public List<Integer> getWeapons() {
            return weapons;
        }

        @Override
        public String toString() {
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < this.weapons.size(); i++) {
                answer.append(this.weapons.get(i)).append(", ");
            }
            return String.format("%s -> %s", name, this.weapons);
        }
    }

    private static class BunkerList {
        private  List<Bunker> bunkers;


    }
}
