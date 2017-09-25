package scoreboard;


import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {

    private Scoreboard scoreboard = new Scoreboard();

    public String processCommand(String commandLine) {
        String[] tokens = commandLine.split("\\s+");
        String command = tokens[0];
        switch (command) {
            case "RegisterUser":
                return registerUser(tokens[1], tokens[2]);
            case "RegisterGame":
                return registerGame(tokens[1], tokens[2]);
            case "AddScore":
                return addScore(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]));
            case "ShowScoreboard":
                return showScoreboard(tokens[1]);
            case "DeleteGame":
                return deleteGame(tokens[1], tokens[2]);
            case "ListGamesByPrefix":
                return listGamesByPrefix(tokens[1]);
            case "\n":
                return "";
            default:
                return "Incorrect command";
        }
    }

    private String registerUser(String username, String userPassword) {
        if (this.scoreboard.registerUser(username, userPassword)) {
            return "User registered";
        }

        return "Duplicated user";
    }

    private String registerGame(String gameName, String gamePassword) {
        if (this.scoreboard.registerGame(gameName, gamePassword)) {
            return "Game registered";
        }

        return "Duplicated game";
    }

    private String addScore(String username, String userPassword, String gameName, String gamePassword, int score) {
        if (this.scoreboard.addScore(username, userPassword, gameName, gamePassword, score)) {
            return "Score added";
        }

        return "Cannot add score";
    }

    private String showScoreboard(String gameName) {
        Iterable<ScoreboardEntry> result = scoreboard.showScoreboard(gameName);

        if (result == null) {
            return "Game not found";
        }

        List<ScoreboardEntry> scoreboardEntries = new ArrayList<>();
        result.forEach(scoreboardEntries::add);
        if (scoreboardEntries.size() > 0) {
            StringBuilder builder = new StringBuilder();
            int counter = 0;
            for (ScoreboardEntry entry : scoreboardEntries) {
                counter++;
                builder.append(String.format("#%s %s %s\r\n", counter, entry.getUserName(), entry.getScore()));
            }

            return builder.toString().trim();
        }

        return "No score";
    }

    private String deleteGame(String gameName, String gamePassword) {
        if (this.scoreboard.deleteGame(gameName, gamePassword)) {
            return "Game deleted";
        }

        return "Cannot delete game";
    }

    private String listGamesByPrefix(String namePrefix) {
        List<String> matchedGames = new ArrayList<>();
        this.scoreboard.listGamesByPrefix(namePrefix).forEach(matchedGames::add);
        if (matchedGames.size() > 0) {
            return String.join(", ", matchedGames);
        }

        return "No matches";
    }
}