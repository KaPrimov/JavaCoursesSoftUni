package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HighScore {
    public static void main(String[] args) throws IOException {
        Map<String, Long> totalScorePerPlayer = new LinkedHashMap<>();
        Map<String,LinkedList<String>> opponentsPerPlayer = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern pattern = Pattern.compile("(\\d+) ([A-Za-z0-9]+)<->([A-Za-z0-9]+) (\\d+)");

        while (true) {
            String line = reader.readLine();

            if("osu!".equals(line)) {
                break;
            }

            Matcher matcher = pattern.matcher(line);

            if(matcher.find()) {
                long scorePlayer1 = Long.parseLong(matcher.group(1));
                long scorePlayer2 = Long.parseLong(matcher.group(4));
                String player1 = matcher.group(2);
                String player2 = matcher.group(3);

                if(!totalScorePerPlayer.containsKey(player1)) {
                    totalScorePerPlayer.put(player1,0L);
                    opponentsPerPlayer.put(player1, new LinkedList<>());
                }

                if(!totalScorePerPlayer.containsKey(player2)) {
                    totalScorePerPlayer.put(player2, 0L);
                    opponentsPerPlayer.put(player2, new LinkedList<>());
                }

                if(scorePlayer1 > scorePlayer2) {
                    totalScorePerPlayer.put(player1, totalScorePerPlayer.get(player1) + (scorePlayer1 - scorePlayer2));
                    totalScorePerPlayer.put(player2, totalScorePerPlayer.get(player2) - (scorePlayer1 - scorePlayer2));
                } else {
                    totalScorePerPlayer.put(player1, totalScorePerPlayer.get(player1) -(scorePlayer2 - scorePlayer1) );
                    totalScorePerPlayer.put(player2, totalScorePerPlayer.get(player2) + (scorePlayer2 - scorePlayer1));
                }

//                totalScorePerPlayer.put(player1, totalScorePerPlayer.get(player1) + scorePlayer1);
//                totalScorePerPlayer.put(player2, totalScorePerPlayer.get(player2) + scorePlayer2);

                    String opponentPlayer1 = String.format("*   %s <-> %d", player2, scorePlayer1-scorePlayer2);
                    String opponentPlayer2 = String.format("*   %s <-> %d", player1, scorePlayer2-scorePlayer1);


                    opponentsPerPlayer.get(player1).add(opponentPlayer1);
                    opponentsPerPlayer.get(player2).add(opponentPlayer2);

            }

        }

        totalScorePerPlayer
                .entrySet()
                .stream()
                .sorted((a, b) -> {
                    return b.getValue().compareTo(a.getValue());
                })
                .forEach(player -> {
                    System.out.printf("%s - (%d)%n", player.getKey(), player.getValue());
                    opponentsPerPlayer.get(player.getKey()).forEach(System.out::println);
                });

    }
}
