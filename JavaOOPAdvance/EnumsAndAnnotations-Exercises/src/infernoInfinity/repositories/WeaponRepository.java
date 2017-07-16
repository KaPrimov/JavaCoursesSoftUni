package infernoInfinity.repositories;

import infernoInfinity.weapons.interfaces.Weapon;

import java.util.HashMap;
import java.util.Map;

public class WeaponRepository {

    private Map<String, Weapon> weapons;

    public WeaponRepository() {
        this.weapons = new HashMap<>();
    }

    public void addWeapon(Weapon weapon) {
        this.weapons.putIfAbsent(weapon.getName(), weapon);
    }

    public Weapon getWeapon(String weaponName) {
        return this.weapons.get(weaponName);
    }
}
