package tasks.phonebook;

import tasks.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashTable<String, String> phonebook = new HashTable<>();
        String line = "";
        while (!"search".equals(line = reader.readLine())) {
            String[] tokens = line.split("-");
            phonebook.addOrReplace(tokens[0], tokens[1]);
        }

        while (!"".equals(line = reader.readLine())) {
            if(phonebook.containsKey(line)) {
                System.out.println(line + " -> " + phonebook.get(line));
            } else {
                System.out.println("Contact " + line + " does not exist.");
            }
        }
    }
}
