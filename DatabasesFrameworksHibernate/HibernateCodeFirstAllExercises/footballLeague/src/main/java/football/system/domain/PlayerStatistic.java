package football.system.domain;

import football.system.domain.ids.PlayerStatisticId;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "player_statistics")
public class PlayerStatistic {

    private PlayerStatisticId playerStatisticId;
    private Integer scoreGoals;
    private Double playerAssists;
    private Double minutesPlayesInGame;

    public PlayerStatistic() {
    }

    @EmbeddedId
    public PlayerStatisticId getPlayerStatisticId() {
        return playerStatisticId;
    }

    public void setPlayerStatisticId(PlayerStatisticId playerStatisticId) {
        this.playerStatisticId = playerStatisticId;
    }

    @Basic
    public Integer getScoreGoals() {
        return scoreGoals;
    }

    public void setScoreGoals(Integer scoreGoals) {
        this.scoreGoals = scoreGoals;
    }

    @Basic
    public Double getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(Double playerAssists) {
        this.playerAssists = playerAssists;
    }

    @Basic
    public Double getMinutesPlayesInGame() {
        return minutesPlayesInGame;
    }

    public void setMinutesPlayesInGame(Double minutesPlayesInGame) {
        this.minutesPlayesInGame = minutesPlayesInGame;
    }
}
