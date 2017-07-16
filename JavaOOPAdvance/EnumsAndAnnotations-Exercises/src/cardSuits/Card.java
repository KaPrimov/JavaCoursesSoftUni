package cardSuits;

public class Card implements Comparable<Card> {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    private int getPower() {
        return this.suit.getPower() + this.rank.getPower();
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rank != card.rank) return false;
        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (suit != null ? suit.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Card card) {
        return Integer.compare(this.getPower(), card.getPower());
    }
}
