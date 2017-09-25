package scoreboard;

import java.util.*;

public class Scoreboard {

    private Map<String, String> usernamesPasswords;
    private SortedMap<String, String> gamesPasswords;
    private Map<String, SortedSet<ScoreboardEntry>> scoresByGame;

    public Scoreboard() {
        this.usernamesPasswords = new HashMap<>();
        this.gamesPasswords = new TreeMap<>();
        this.scoresByGame = new HashMap<>();
    }

    public boolean registerUser(String username, String password) {
        if (this.usernamesPasswords.containsKey(username)) {
            return false;
        }
        this.usernamesPasswords.put(username, password);
        return true;
    }

    public boolean registerGame(String game, String password) {
        if (this.gamesPasswords.containsKey(game)) {
            return false;
        }
        this.gamesPasswords.put(game, password);
        this.scoresByGame.put(game, new TreeSet<>());
        return true;
    }

    public boolean addScore(String username, String userPassword, String game, String gamePassword, int score) {
        boolean gameIsNotPresent = !this.gamesPasswords.containsKey(game);
        boolean userIsNotPresent = !this.usernamesPasswords.containsKey(username);

        if (gameIsNotPresent || userIsNotPresent) {
            return false;
        }

        boolean userPasswordDoesNotMatch = !this.usernamesPasswords.get(username).equals(userPassword);
        boolean gamePasswordDoesNotMatch = !this.gamesPasswords.get(game).equals(gamePassword);

        if(userPasswordDoesNotMatch || gamePasswordDoesNotMatch) {
            return false;
        }

        ScoreboardEntry scoreboardEntry = new ScoreboardEntry(score, username);
        this.scoresByGame.get(game).add(scoreboardEntry);

        return true;
    }

    public Iterable<ScoreboardEntry> showScoreboard(String game) {
        if (!this.gamesPasswords.containsKey(game)) {
            return null;
        }
        List<ScoreboardEntry> scoreboardEntries = new ArrayList<>();

        int count = 1;
        for (ScoreboardEntry scoreboardEntry : this.scoresByGame.get(game)) {
            scoreboardEntries.add(scoreboardEntry);
            count++;
            if(count == 10) {
                break;
            }
        }
        return scoreboardEntries;
    }

    public boolean deleteGame(String game, String gamePassword) {
        if (!this.gamesPasswords.containsKey(game) || !this.gamesPasswords.get(game).equals(gamePassword)) {
            return false;
        }

        this.gamesPasswords.remove(game);
        this.scoresByGame.remove(game);
        return true;
    }

    public Iterable<String> listGamesByPrefix(String gameNamePrefix) {
        Set<String> prefixes = this.gamesPasswords.subMap(gameNamePrefix, gameNamePrefix + Character.MAX_VALUE).keySet();

        List<String> top10Games = new ArrayList<>();
        for (String game : prefixes) {
            top10Games.add(game);
            if(top10Games.size() == 10) {
                break;
            }
        }
        return top10Games;
    }
}
