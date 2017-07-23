package football.system.domain;

import football.system.domain.ids.BetGamesId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "bed_games")
public class BetGame {

    private BetGamesId betGamesId;
    private ResultPrediction resultPrediction;

    public BetGame() {
    }

    @EmbeddedId
    public BetGamesId getBetGamesId() {
        return betGamesId;
    }

    public void setBetGamesId(BetGamesId betGamesId) {
        this.betGamesId = betGamesId;
    }

    @ManyToOne
    @JoinColumn(name = "prediction_id")
    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
