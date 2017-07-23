package football.system.domain.ids;

import football.system.domain.Bet;
import football.system.domain.Game;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BetGamesId implements Serializable{
    private Game game;
    private Bet bet;

    public BetGamesId() {
    }

    @ManyToOne
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne
    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }
}
