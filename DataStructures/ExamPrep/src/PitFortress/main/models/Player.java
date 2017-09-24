package PitFortress.main.models;

import PitFortress.main.interfaces.IPlayer;

public class Player implements IPlayer {

    private int radius;
    private String name;
    private int score;

    public Player(int radius, String name) {
        this.setRadius(radius);
        this.setName(name);
        this.setScore(0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getScore() {
        return score;
    }

    private void setRadius(int radius) {
        if(radius < 0) {
            throw new IllegalArgumentException();
        }
        this.radius = radius;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setScore(int value) {
        this.score = value;
    }

    @Override
    public int compareTo(Player o) {
        int index = Integer.compare(this.score, o.score);
        if(index == 0) {
            index = this.name.compareTo(o.name);
        }
        return index;
    }
}
