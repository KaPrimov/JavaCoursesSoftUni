package PitFortress.main.models;

import PitFortress.main.interfaces.IMinion;

public class Minion implements IMinion {

    private int id;
    private int x;
    private int health;

    public Minion(int id, int x) {
        this.setId(id);
        this.setX(x);
        setHealth(100);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getHealth() {
        return health;
    }

    private void setHealth(int value) {
        this.health = value;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setX(int x) {
        if (x < 0 || x > 1_000_000) {
            throw new IllegalArgumentException();
        }
        this.x = x;
    }

    @Override
    public int compareTo(Minion o) {
        int index = Integer.compare(this.getX(), o.getX());
        if (index == 0) {
            index = Integer.compare(this.getId(), o.getId());
        }
        return index;
    }

    public void takeDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }
}
