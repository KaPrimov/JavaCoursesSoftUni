package infernoInfinity.io.commands;

import infernoInfinity.enums.Gem;
import infernoInfinity.repositories.WeaponRepository;

public class AddCommand implements Command {

    private WeaponRepository weaponRepository;

    public AddCommand(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public void execute(String[] tokens) {
        String weaponName = tokens[1];
        int socketIndex = Integer.parseInt(tokens[2]);
        Gem gem = Gem.valueOf(tokens[3]);
        weaponRepository.getWeapon(weaponName).addGem(gem, socketIndex);
    }
}
