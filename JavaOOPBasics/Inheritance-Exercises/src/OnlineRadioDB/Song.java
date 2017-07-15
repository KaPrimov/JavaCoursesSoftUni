package OnlineRadioDB;

public class Song {
    private String name;
    private int minutes;
    private int seconds;

    public Song(String name, int minutes, int seconds) {
        this.setName(name);
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 3 || name.trim().length() > 30){
            throw new InvalidSongNameException();
        }
        this.name = name;
    }

    private void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 14){
            throw new InvalidSongMinutesException();
        }
        this.minutes = minutes;
    }

    private void setSeconds(int seconds) {
        if (seconds < 0 || seconds > 59){
            throw new InvalidSongSecondsException();
        }
        this.seconds = seconds;
    }
}
