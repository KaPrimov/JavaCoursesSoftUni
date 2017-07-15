package OnlineRadioDB;

public class InvalidArtistNameException extends InvalidSongException {

    private static final String INVALID_ARTIST_NAME = "Artist name should be between 3 and 20 symbols.";

    public InvalidArtistNameException() {
        super(INVALID_ARTIST_NAME);
    }
}
