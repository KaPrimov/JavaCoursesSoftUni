package infernoInfinity.io.commands;

import infernoInfinity.io.interfaces.Writer;
import infernoInfinity.repositories.WeaponRepository;
import infernoInfinity.weapons.interfaces.Weapon;

public class CompareCommand implements Command {

    private WeaponRepository weaponRepository;
    private Writer outputWriter;

    public CompareCommand(WeaponRepository weaponRepository, Writer outputWriter) {
        this.weaponRepository = weaponRepository;
        this.outputWriter = outputWriter;
    }

    @Override
    public void execute(String[] command) {
        Weapon firstWeapon = weaponRepository.getWeapon(command[1]);
        Weapon secondWeapon = weaponRepository.getWeapon(command[2]);

        if(firstWeapon.compareTo(secondWeapon) >= 0) {
            outputWriter.printLevel(firstWeapon);
        } else {
            outputWriter.printLevel(secondWeapon);
        }
    }
}
