package PitFortress.main;

import PitFortress.main.interfaces.IPitFortress;
import PitFortress.main.models.Mine;
import PitFortress.main.models.Minion;
import PitFortress.main.models.Player;

import java.util.*;

public class PitFortressCollection implements IPitFortress {

    private Map<String, Player> namePlayers;
    private Set<Minion> sortedMinions;
    private SortedMap<Integer, List<Minion>> xCoordMinions;
    private Set<Player> sortedPlayersAsc;
    private Set<Player> sortedPlayersDesc;
    private Set<Mine> sortedMines;
    private int minionsId;
    private int minesId;

    public PitFortressCollection() {
        this.namePlayers = new HashMap<>();
        this.sortedMines = new TreeSet<>();
        this.sortedPlayersAsc = new TreeSet<>();
        this.sortedPlayersDesc = new TreeSet<>(Comparator.reverseOrder());
        this.sortedMinions = new TreeSet<>();
        this.xCoordMinions = new TreeMap<>();
        this.minesId = 1;
        this.minionsId = 1;
    }

    @Override
    public int getPlayerCount() {
        return this.namePlayers.size();
    }

    @Override
    public int getMinionCount() {
        return this.sortedMinions.size();
    }

    @Override
    public int getMineCount() {
        return this.sortedMines.size();
    }

    @Override
    public void addPlayer(String name, int mineRadius) {
        if (namePlayers.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        Player player = new Player(mineRadius, name);
        this.namePlayers.put(name, player);
        this.sortedPlayersDesc.add(player);
        this.sortedPlayersAsc.add(player);
    }

    @Override
    public void addMinion(int xCoordinate) {
        Minion minion = new Minion(this.minionsId, xCoordinate);
        this.xCoordMinions.computeIfAbsent(xCoordinate, x -> new ArrayList<>());
        this.xCoordMinions.get(xCoordinate).add(minion);
        this.sortedMinions.add(minion);
        this.minionsId++;
    }

    @Override
    public void setMine(String playerName, int xCoordinate, int delay, int damage) {
        Mine mine = new Mine(this.minesId, delay, xCoordinate, this.namePlayers.getOrDefault(playerName, null), damage);
        this.sortedMines.add(mine);
        this.minesId++;
    }

    @Override
    public Iterable<Minion> reportMinions() {
        return this.sortedMinions;
    }

    @Override
    public Iterable<Player> top3PlayersByScore() {
        if(this.sortedPlayersDesc.size() < 3) {
            throw new IllegalArgumentException();
        }
        List<Player> topPlayers = new ArrayList<>();
        for (Player player : this.sortedPlayersDesc) {
            topPlayers.add(player);
            if(topPlayers.size() == 3) {
                break;
            }
        }
        return topPlayers;
    }

    @Override
    public Iterable<Player> min3PlayersByScore() {
        if(this.sortedPlayersAsc.size() < 3) {
            throw new IllegalArgumentException();
        }
        List<Player> bottomPlayers = new ArrayList<>();
        for (Player player : this.sortedPlayersAsc) {
            bottomPlayers.add(player);
            if(bottomPlayers.size() == 3) {
                break;
            }
        }
        return bottomPlayers;
    }

    @Override
    public Iterable<Mine> getMines() {
        return this.sortedMines;
    }

    @Override
    public void playTurn() {
        List<Mine> destroyedMines = new ArrayList<>();
        List<Minion> destroyedMinions = new ArrayList<>();

        for (Mine mine : this.sortedMines) {
            mine.passTurn();
            if (mine.getDelay() == 0) {
                SortedMap<Integer, List<Minion>> minionsInBlastRadius = this.xCoordMinions
                        .subMap(mine.getX() - mine.getPlayer().getRadius(), mine.getX() + mine.getPlayer().getRadius() + 1);
                for (List<Minion> minions : minionsInBlastRadius.values()) {
                    for (Minion minion : minions) {

                        minion.takeDamage(mine.getDamage());
                        if(minion.getHealth() <= 0 && !destroyedMinions.contains(minion)) {
                            Player player = mine.getPlayer();
                            this.sortedPlayersAsc.remove(player);
                            this.sortedPlayersDesc.remove(player);
                            player.setScore(player.getScore() + 1);
                            this.sortedPlayersAsc.add(player);
                            this.sortedPlayersDesc.add(player);
                            this.sortedMinions.remove(minion);
                            destroyedMinions.add(minion);
                        }
                    }
                }
                destroyedMines.add(mine);
            }
        }
        for (Minion destroyedMinion : destroyedMinions) {
            this.xCoordMinions.get(destroyedMinion.getX()).remove(destroyedMinion);
        }

        for (Mine destroyedMine : destroyedMines) {
            this.sortedMines.remove(destroyedMine);
        }
    }
}
