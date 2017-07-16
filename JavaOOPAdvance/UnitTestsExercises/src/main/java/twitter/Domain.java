package twitter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Domain {

    private List<Client> clients;
    private List<Tweet> tweets;

    public Domain() {
        this.clients = new ArrayList<Client>();
        this.tweets = new ArrayList<Tweet>();
    }

    public void addMessage(Tweet tweet) {
        tweets.add(tweet);
    }

}
