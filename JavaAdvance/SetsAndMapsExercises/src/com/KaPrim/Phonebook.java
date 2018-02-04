package com.KaPrim;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> phonebook = new HashMap<>();
        String[] command = scanner.nextLine().split("-");

        while (!command[0].equals("search")) {
            String name = command[0];
            String number = command[1];
            if(!phonebook.containsKey(name)) {
                phonebook.put(name, number);
            } else {
                phonebook.replace(name, number);
            }
            command = scanner.nextLine().split("-");
        }

        String searchedName = scanner.nextLine();
        while (!searchedName.equals("stop")) {
            if (phonebook.containsKey(searchedName)) {
                System.out.println(searchedName + " -> " + phonebook.get(searchedName));
            } else {
                System.out.printf("Contact %s does not exist.\n", searchedName );
            }
            searchedName = scanner.nextLine();
        }

    }
}
