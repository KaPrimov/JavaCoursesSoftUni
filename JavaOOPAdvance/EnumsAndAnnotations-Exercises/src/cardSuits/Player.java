package cardSuits;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Player implements Comparable<Player> {
    private String name;
    private Set<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new TreeSet<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getHandSize() {
        return this.hand.size();
    }

    private Card getHighestPower() {
        return this.hand.stream().sorted(Comparator.reverseOrder()).findFirst().orElse(null);
    }

    @Override
    public int compareTo(Player o) {
        return this.getHighestPower().compareTo(o.getHighestPower());
    }

    @Override
    public String toString() {
        Card card = this.getHighestPower();
        return String.format("%s wins with %s.", this.name, card);
    }
}
