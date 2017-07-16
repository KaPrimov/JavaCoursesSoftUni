package infernoInfinity.factories;

import infernoInfinity.weapons.Axe;
import infernoInfinity.weapons.Knife;
import infernoInfinity.weapons.Sword;
import infernoInfinity.weapons.interfaces.Weapon;

public class WeaponFactory {

    private static final WeaponFactory WEAPON_FACTORY = new WeaponFactory();

    private WeaponFactory() {
    }

    public static WeaponFactory getInstance() {
        return WEAPON_FACTORY;
    }

    public Weapon createWeapon(String... args) {
        String type = args[1];
        String name = args[2];
        Weapon weapon = null;
        switch (type) {
            case "AXE":
                weapon = new Axe(name);
                break;
            case "SWORD":
                weapon = new Sword(name);
                break;
            case "KNIFE":
                weapon = new Knife(name);
                break;
        }

        return weapon;
    }

}
