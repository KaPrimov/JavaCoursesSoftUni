package infernoInfinity.io.commands;

import infernoInfinity.annotations.WeaponInfo;
import infernoInfinity.io.interfaces.Writer;
import infernoInfinity.weapons.WeaponImpl;


public class DescriptionCommand implements Command {

    private Writer outputWriter;

    public DescriptionCommand(Writer outputWriter) {
        this.outputWriter = outputWriter;
    }

    @Override
    public void execute(String[] command) {
        WeaponInfo annotation = WeaponImpl.class.getAnnotation(WeaponInfo.class);
        outputWriter.print("Class description: " + annotation.description());
    }

}
