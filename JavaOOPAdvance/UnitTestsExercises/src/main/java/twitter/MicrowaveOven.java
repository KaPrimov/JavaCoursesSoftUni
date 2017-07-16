package twitter;

import java.util.ArrayList;
import java.util.List;

public class MicrowaveOven implements Client {

    private String model;
    private List<Tweet> tweets;

    public MicrowaveOven(String model) {
        this.model = model;
        this.tweets = new ArrayList<Tweet>();
    }

    public void processTweet(Tweet tweet, Domain domain) {
        System.out.println(tweet.retrieveMessage());
        domain.addMessage(tweet);
    }
}
