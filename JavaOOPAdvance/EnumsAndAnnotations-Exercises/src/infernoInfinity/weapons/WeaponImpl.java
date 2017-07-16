package infernoInfinity.weapons;

import infernoInfinity.annotations.WeaponInfo;
import infernoInfinity.enums.Gem;
import infernoInfinity.enums.Stat;
import infernoInfinity.weapons.interfaces.Weapon;

@WeaponInfo(
        author = "Pesho",
        revision = 3,
        description = "Used for Java OOP Advanced course - Enumerations and Annotations.",
        reviewers = {"Pesho", "Svetlio"}
)
public abstract class WeaponImpl implements Weapon {

    private String name;
    private int minDamage;
    private int initialMinDamage;
    private int maxDamage;
    private int initialMaxDamage;
    private int strength;
    private int agility;
    private int vitality;
    private Gem[] gems;
    private double level;

    protected WeaponImpl(String name, int minDamage, int maxDamage, int gemSpaces) {
        this.name = name;
        this.initialMinDamage = minDamage;
        this.initialMaxDamage = maxDamage;
        this.gems = new Gem[gemSpaces];
    }

    @Override
    public void addGem(Gem gem, int index) {
        if (index < gems.length && index >= 0) {
            this.gems[index] = gem;
        }
    }

    @Override
    public void remove(int index) {
        if (index < gems.length && index >= 0) {
            this.gems[index] = null;
        }
    }

    @Override
    public double getLevel() {
        return this.level;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        this.calculateLevel();
        return String.format("%s: %d-%d Damage, +%d Strength, " +
                        "+%d Agility, +%d Vitality",
                this.name, this.minDamage, this.maxDamage,
                this.strength, this.agility, this.vitality);
    }

    @Override
    public int compareTo(Weapon other) {
        this.calculateLevel();
        other.calculateLevel();
        return (int) (this.getLevel() - other.getLevel());
    }

    @Override
    public void calculateLevel() {
        this.calculateStats();
        double level =  (((double)this.minDamage + (double)this.maxDamage) / 2.0) + this.strength + this.agility + this.vitality;
        this.level = level;
    }

    private void calculateStats() {
        this.strength = 0;
        this.agility = 0;
        this.vitality = 0;
        for (Gem gem : gems) {
            if (gem != null) {
                switch (gem) {
                    case RUBY:
                        this.strength += Gem.RUBY.getStrength();
                        this.agility += Gem.RUBY.getAgility();
                        this.vitality += Gem.RUBY.getVitality();
                        break;
                    case EMERALD:
                        this.strength += Gem.EMERALD.getStrength();
                        this.agility += Gem.EMERALD.getAgility();
                        this.vitality += Gem.EMERALD.getVitality();
                        break;
                    case AMETHYST:
                        this.strength += Gem.AMETHYST.getStrength();
                        this.agility += Gem.AMETHYST.getAgility();
                        this.vitality += Gem.AMETHYST.getVitality();
                        break;
                }
            }
            this.calculateDamage();
        }
    }

    private void calculateDamage() {
        this.maxDamage = this.initialMaxDamage + (this.strength * Stat.STRENGTH.getMaxDamage()) +
                (this.agility * Stat.AGILITY.getMaxDamage());

        this.minDamage = this.initialMinDamage + (this.strength * Stat.STRENGTH.getMinDamage()) +
                (this.agility * Stat.AGILITY.getMinDamage());
    }


}
