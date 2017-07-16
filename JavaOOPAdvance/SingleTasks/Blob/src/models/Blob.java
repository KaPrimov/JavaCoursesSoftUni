package models;

import interfaces.Attack;
import interfaces.Behavior;
import interfaces.Blobable;

public class Blob implements Blobable {

    private String name;
    private int currentHealth;
    private int damage;
    private Behavior behavior;
    private Attack attack;

    private int initialHealth;
    private int initialDamage;

    public Blob(String name, int health, int damage, Behavior behavior, Attack attack) {
        this.name = name;
        this.currentHealth = health;
        this.damage = damage;
        this.behavior = behavior;
        this.attack = attack;

        this.initialHealth = health;
        this.initialDamage = damage;
    }

    @Override
    public void attack(Blobable attacker, Blobable target) {
        this.attack.execute(attacker, target);
    }

    @Override
    public int getHealth() {
        return this.currentHealth;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void sufferAttack(int health) {
        if(this.currentHealth - health < 0) {
            this.currentHealth = 0;
        } else {
            this.currentHealth = currentHealth - health;
        }
    }

    @Override
    public void backfiredAttack(int health) {
        if(this.currentHealth - health > 1) {
            this.currentHealth -= health;
        }
    }

    @Override
    public void updateDamage(int damage) {
        if (damage >= this.initialDamage) {
            this.damage = damage;
        }
    }

    @Override
    public void updateHealth(int health) {
        if(this.currentHealth + health >= 0) {
            this.currentHealth += health;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update() {
        if(this.behavior.isTriggered()) {
            this.behavior.applyRecurrentEffect(this);
        }
        if(this.currentHealth <= this.initialHealth / 2 && !this.behavior.isTriggered()) {
            this.behavior.trigger(this);
        }
    }

    @Override
    public String toString() {
        if (this.getHealth() <= 0) {
            return String.format("Blob %s KILLED", this.getName());
        }

        return String.format("Blob %s: %s HP, %s Damage", this.getName(), this.getHealth(), this.getDamage());
    }
}

