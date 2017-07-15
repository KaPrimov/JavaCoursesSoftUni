package PokemonTrainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Trainer> trainers = new LinkedHashMap<>();

        while (true) {
            String[] tokens = reader.readLine().split(" ");

            if(tokens[0].equals("Tournament")) {
                break;
            }

            String trainerName = tokens[0];

            if(!trainers.containsKey(trainerName)) {
                trainers.put(trainerName, new Trainer(trainerName));
            }

            String pokemonName = tokens[1];
            String element = tokens[2];
            int pokemonHP = Integer.parseInt(tokens[3]);

            Pokemon pokemon = new Pokemon(pokemonName, element, pokemonHP);

            trainers.get(trainerName).addPokemon(pokemon);

        }

        while(true) {
            String element = reader.readLine();
            if("End".equals(element)) {
                break;
            }

            for (Trainer trainer : trainers.values()) {
                trainer.tournamentRound(element);
            }
        }

        trainers
                .entrySet()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getValue().getBadges(), a.getValue().getBadges()))
                .forEach(t -> System.out.println(t.getValue().toString()));

    }
}
