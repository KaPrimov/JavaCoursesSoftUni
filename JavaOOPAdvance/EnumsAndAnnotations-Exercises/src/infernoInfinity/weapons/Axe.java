package infernoInfinity.weapons;

import infernoInfinity.enums.WeaponStats;

public class Axe extends WeaponImpl {

    public Axe(String name) {
        super(name, WeaponStats.AXE.getMinDamage(), WeaponStats.AXE.getMaxDamage(), WeaponStats.AXE.getSockets());
    }
}
