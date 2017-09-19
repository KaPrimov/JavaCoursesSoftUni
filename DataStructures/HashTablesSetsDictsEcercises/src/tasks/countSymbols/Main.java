package tasks.countSymbols;

import tasks.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        HashTable<Character, Integer> symbolsCount = new HashTable<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word = reader.readLine();
        for (int i = 0; i < word.length(); i++) {
            if(!symbolsCount.containsKey(word.charAt(i))) {
               symbolsCount.add(word.charAt(i), 0);
            }
            symbolsCount.addOrReplace(word.charAt(i), symbolsCount.get(word.charAt(i)) + 1);
        }

        for (Character character : symbolsCount.sortedAscendingKeys()) {
            System.out.printf("%s: %d time/s%n",
                    character, symbolsCount.get(character));
        }
    }
}
