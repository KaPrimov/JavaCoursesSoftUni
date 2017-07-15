package OnlineRadioDB;

public class InvalidSongLengthException extends InvalidSongException {

    private static final String INVALID_SONG_LENGTH = "Invalid song length.";

    public InvalidSongLengthException() {
        super(INVALID_SONG_LENGTH);
    }

    public InvalidSongLengthException(String message) {
        super(message);
    }
}
