package infernoInfinity.weapons.interfaces;

import infernoInfinity.enums.Gem;

public interface Weapon extends Comparable<Weapon>, Leveble {

    void addGem(Gem gem, int index);
    void remove(int index);
    String getName();

}
