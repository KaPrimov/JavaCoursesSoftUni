package PitFortress.main.models;

import PitFortress.main.interfaces.IMine;

public class Mine implements IMine {

    private int id;
    private int delay;
    private int x;
    private Player player;
    private int damage;

    public Mine(int id, int delay, int x, Player player, int damage) {
        this.setId(id);
        this.setDelay(delay);
        this.setX(x);
        this.setPlayer(player);
        this.setDamage(damage);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    public void setDelay(int value) {
        if (value < 1 || value > 10_000) {
            throw new IllegalArgumentException();
        }
        this.delay = value;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int compareTo(Mine o) {
        int index = Integer.compare(this.getDelay(), o.getDelay());
        if (index == 0) {
            index = Integer.compare(this.getId(), o.getId());
        }
        return index;
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

    private void setPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException();
        }
        this.player = player;
    }

    private void setDamage(int damage) {
        if (damage < 0 || damage > 100) {
            throw new IllegalArgumentException();
        }
        this.damage = damage;
    }

    public void passTurn() {
        this.delay--;
    }
}
