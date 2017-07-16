package twitterTests;

import org.junit.Before;
import org.junit.Test;
import twitter.*;

public class TwitterTests {

    private Domain domain;
    private Tweet tweet;
    private Client client;

    @Before
    public void initializeObjects() {
        this.domain = new Domain();
        this.tweet = new TweetImpl("this is my first tweet");
        this.client = new MicrowaveOven("Rahovec");
    }
}
