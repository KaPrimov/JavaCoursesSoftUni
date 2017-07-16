package infernoInfinity.io.commands;

import infernoInfinity.repositories.WeaponRepository;

public class RemoveCommand implements Command {

    private WeaponRepository weaponRepository;

    public RemoveCommand(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public void execute(String[] command) {
        weaponRepository.getWeapon(command[1]).remove(Integer.parseInt(command[2]));
    }
}
