package infernoInfinity.io.commands;

import infernoInfinity.factories.WeaponFactory;
import infernoInfinity.repositories.WeaponRepository;
import infernoInfinity.weapons.interfaces.Weapon;

public class CreateCommand implements Command {

    private WeaponRepository weaponRepository;

    public CreateCommand(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public void execute(String[] tokens) {
        Weapon weapon = WeaponFactory.getInstance().createWeapon(tokens);
        weaponRepository.addWeapon(weapon);
    }
}
