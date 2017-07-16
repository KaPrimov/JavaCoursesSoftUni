package cardSuits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    private static HashSet<Card> deck;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        deck = new HashSet<>();
        Player firstPlayer = new Player(reader.readLine());
        Player secondPlayer = new Player(reader.readLine());
        addPlayer(firstPlayer, reader);
        addPlayer(secondPlayer, reader);

        Player winner = firstPlayer;
        if (secondPlayer.compareTo(firstPlayer) > 0) {
            winner = secondPlayer;
        }
        System.out.println(winner);
    }

    private static void addPlayer(Player player, BufferedReader reader) throws IOException {
        while (player.getHandSize() < 5) {
            String[] tokens = reader.readLine().split("[\\sof]+");
            Rank rank = null;
            Suit suit = null;

            try {
                rank = Rank.valueOf(tokens[0]);
                suit = Suit.valueOf(tokens[1]);
            } catch (Exception e) {
                System.out.println("No such card exists.");
                continue;
            }

            Card card = new Card(rank, suit);

            if(deck.contains(card)) {
                System.out.println("Card is not in the deck.");
                continue;
            }

            player.addCard(card);
            deck.add(card);
        }
    }
}
