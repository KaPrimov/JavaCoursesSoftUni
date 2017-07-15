package OnlineRadioDB;

public class InvalidSongNameException extends InvalidSongException {

    public static final String INVALID_SONG_NAME = "Song name should be between 3 and 30 symbols.";

    public InvalidSongNameException() {
        super(INVALID_SONG_NAME);
    }
}
