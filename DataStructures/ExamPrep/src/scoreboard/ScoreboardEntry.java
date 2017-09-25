package scoreboard;

public class ScoreboardEntry implements Comparable<ScoreboardEntry> {
    private int score;
    private String userName;

    public ScoreboardEntry(int score, String userName) {
        this.score = score;
        this.userName = userName;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public int compareTo(ScoreboardEntry other) {
        int index = Integer.compare(other.score, this.score);

        if (index == 0) {
            index = this.userName.compareTo(other.userName);
        }

        return index;
    }
}