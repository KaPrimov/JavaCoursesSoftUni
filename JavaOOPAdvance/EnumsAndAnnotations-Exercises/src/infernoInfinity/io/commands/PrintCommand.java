package infernoInfinity.io.commands;

import infernoInfinity.io.interfaces.Writer;
import infernoInfinity.repositories.WeaponRepository;

public class PrintCommand implements Command {

    private WeaponRepository weaponRepository;
    private Writer outputWriter;

    public PrintCommand(WeaponRepository weaponRepository, Writer outputWriter) {
        this.weaponRepository = weaponRepository;
        this.outputWriter = outputWriter;
    }


    @Override
    public void execute(String[] command) {

        outputWriter.print(weaponRepository.getWeapon(command[1]).toString());
    }
}
