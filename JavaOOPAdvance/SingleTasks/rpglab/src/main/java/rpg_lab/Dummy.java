package rpg_lab;

import java.util.List;

public class Dummy implements Target {

    private int health;
    private int experience;
    private List<Weapon> loot;

    public Dummy(int health, int experience, List<Weapon> loot) {
        this.health = health;
        this.experience = experience;
        this.loot = loot;
    }

    public int getHealth() {
        return this.health;
    }

    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        }

        this.health -= attackPoints;
    }

    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.experience;
    }

    public Weapon dropWeapon(RandomProvider random) {
        if(!isDead()) {
            throw new IllegalStateException("Dummy is not dead");
        }
        int index = random.nextInt(this.loot.size());
        return loot.get(index);
    }

    public boolean isDead() {
        return this.health <= 0;
    }
}
