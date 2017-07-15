package OnlineRadioDB;

public class InvalidSongMinutesException extends InvalidSongLengthException {

    private static final String INVALID_SONG_MINUTES = "Song minutes should be between 0 and 14.";

    public InvalidSongMinutesException() {
        super(INVALID_SONG_MINUTES);
    }
}
