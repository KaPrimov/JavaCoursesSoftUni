package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChampionsLeague {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> teamsWins = new HashMap<>();
        Map<String, ArrayList<String>> teamsOpponents = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern pattern = Pattern.compile("(.+) \\| (.+) \\| (\\d+):(\\d+) \\| (\\d+):(\\d+)");
        while (true) {
            String line = reader.readLine();

            if("stop".equals(line)) {
                break;
            }

            Matcher matcher = pattern.matcher(line);

            if(matcher.find()) {
                String homeTeam = matcher.group(1);
                String awayTeam = matcher.group(2);
                int firstTeamHomeGoals = Integer.parseInt(matcher.group(3));
                int secondTeamAwayGoals = Integer.parseInt(matcher.group(4));
                int secondTeamHomeGoals = Integer.parseInt(matcher.group(5));
                int firstTeamAwayGoals = Integer.parseInt(matcher.group(6));
                int totalFirst = firstTeamAwayGoals + firstTeamHomeGoals;
                int totalSecond = secondTeamAwayGoals + secondTeamHomeGoals;

                if(!teamsWins.containsKey(homeTeam)) {
                    teamsWins.put(homeTeam, 0);
                    teamsOpponents.put(homeTeam, new ArrayList<>());
                }

                if(!teamsWins.containsKey(awayTeam)) {
                    teamsWins.put(awayTeam, 0);
                    teamsOpponents.put(awayTeam, new ArrayList<>());
                }

                teamsOpponents.get(homeTeam).add(awayTeam);
                teamsOpponents.get(awayTeam).add(homeTeam);

                if(totalFirst > totalSecond) {
                    teamsWins.put(homeTeam, teamsWins.get(homeTeam) + 1);
                } else if (totalFirst < totalSecond) {
                    teamsWins.put(awayTeam, teamsWins.get(awayTeam) + 1);
                } else {
                    if(firstTeamAwayGoals > secondTeamAwayGoals) {
                        teamsWins.put(homeTeam, teamsWins.get(homeTeam) + 1);
                    } else {
                        teamsWins.put(awayTeam, teamsWins.get(awayTeam) + 1);
                    }
                }
            }
        }

        teamsWins.entrySet()
                .stream()
                .sorted((a, b) -> {
                    int index =  Integer.compare(b.getValue(), a.getValue());

                    if (index == 0) {
                        index = a.getKey().compareTo(b.getKey());
                    }
                    return index;
                })
                .forEach(t -> {
                    System.out.println(t.getKey());
                    System.out.printf("- Wins: %d%n", t.getValue());
                    StringBuilder opponents = new StringBuilder();
                    opponents.append("- Opponents: ");
                    teamsOpponents.get(t.getKey())
                            .stream()
                            .sorted(String::compareTo)
                            .forEach(o -> opponents.append(o).append(", "));
                    System.out.println(opponents.delete(opponents.length()-2, opponents.length()));
                });
    }
}
