package infernoInfinity.weapons;

import infernoInfinity.enums.WeaponStats;

public class Knife extends WeaponImpl  {

    public Knife(String name) {
        super(name, WeaponStats.KNIFE.getMinDamage(), WeaponStats.KNIFE.getMaxDamage(), WeaponStats.KNIFE.getSockets());
    }
}
