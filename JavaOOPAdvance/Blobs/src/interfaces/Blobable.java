package interfaces;

public interface Blobable {
    int getHealth();

    int getDamage();

    void sufferAttack(int health);

    void backfiredAttack(int health);

    void updateDamage(int damage);

    void updateHealth(int health);

    String getName();

    void update();

    void attack(Blobable attacker, Blobable target);
}
