package infernoInfinity.enums;

public enum Stat {
    STRENGTH(2, 3), AGILITY(1, 4), VITALITY(0, 0);

    private int minDamage;
    private int maxDamage;

    Stat(int minDamage, int maxDamage) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}
