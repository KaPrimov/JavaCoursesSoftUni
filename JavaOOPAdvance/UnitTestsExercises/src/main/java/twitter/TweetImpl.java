package twitter;

public class TweetImpl implements Tweet {

    private String message;

    public TweetImpl(String message) {
        this.message = message;
    }


    public String retrieveMessage() {
        return this.message;
    }
}
