package infernoInfinity.weapons;

import infernoInfinity.enums.WeaponStats;

public class Sword extends WeaponImpl {
    public Sword(String name) {
        super(name, WeaponStats.SWORD.getMinDamage(), WeaponStats.SWORD.getMaxDamage(), WeaponStats.SWORD.getSockets());
    }
}
