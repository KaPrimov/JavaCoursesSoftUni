package infernoInfinity.io.commands;

import infernoInfinity.annotations.WeaponInfo;
import infernoInfinity.io.interfaces.Writer;
import infernoInfinity.weapons.WeaponImpl;

public class RevisionCommand implements Command {
    private Writer outputWriter;

    public RevisionCommand(Writer outputWriter) {
        this.outputWriter = outputWriter;
    }

    @Override
    public void execute(String[] command) {
        WeaponInfo annotation = WeaponImpl.class.getAnnotation(WeaponInfo.class);
        outputWriter.print("Revision: " + annotation.revision());
    }
}
