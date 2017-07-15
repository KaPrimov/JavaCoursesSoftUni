package OnlineRadioDB;

public class InvalidSongSecondsException extends InvalidSongLengthException {

    private static final String INVALID_SONG_SECONDS = "Song seconds should be between 0 and 59.";

    public InvalidSongSecondsException() {
        super(INVALID_SONG_SECONDS);
    }
}
