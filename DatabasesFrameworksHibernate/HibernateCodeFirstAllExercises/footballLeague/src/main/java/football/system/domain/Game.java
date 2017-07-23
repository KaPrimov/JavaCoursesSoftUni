package football.system.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "games")
public class Game {
    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private Integer homeGoals;
    private Integer awayGoals;
    private Date timeOfGame;
    private Double homeTeamWinRate;
    private Double awayTeamWinRate;
    private Double drowGameRare;
    private Round round;
    private Competition competition;
    private Set<Player> players;

    public Game() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Basic
    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    @Basic
    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    @Basic
    public Date getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(Date timeOfGame) {
        this.timeOfGame = timeOfGame;
    }


    @Basic
    public Double getHomeTeamWinRate() {
        return homeTeamWinRate;
    }

    public void setHomeTeamWinRate(Double homeTeamWinRate) {
        this.homeTeamWinRate = homeTeamWinRate;
    }

    @Basic
    public Double getAwayTeamWinRate() {
        return awayTeamWinRate;
    }

    public void setAwayTeamWinRate(Double awayTeamWinRate) {
        this.awayTeamWinRate = awayTeamWinRate;
    }

    @Basic
    public Double getDrowGameRare() {
        return drowGameRare;
    }

    public void setDrowGameRare(Double drowGameRare) {
        this.drowGameRare = drowGameRare;
    }

    @ManyToOne
    @JoinColumn(name = "round_id")
    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @ManyToOne
    @JoinColumn(name = "competition_id")
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @ManyToMany
    @JoinTable(name = "player_statistics",
    joinColumns = @JoinColumn(name = "game_id"),
    inverseJoinColumns = @JoinColumn(name = "player_id"))
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
