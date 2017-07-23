package football.system.domain.ids;

import football.system.domain.Game;
import football.system.domain.Player;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PlayerStatisticId implements Serializable {

    private Game game;
    private Player player;

    public PlayerStatisticId() {
    }

    public PlayerStatisticId(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    @ManyToOne
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
