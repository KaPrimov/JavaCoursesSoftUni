package interfaces;

public interface Blobable {
    int getHealth();

    int getDamage();

    String getName();

    void sufferAttack(int health);

    void backfiredAttack(int health);

    void updateDamage(int damage);

    void updateHealth(int health);

    void update();

    void attack(Blobable attacker, Blobable target);
}
