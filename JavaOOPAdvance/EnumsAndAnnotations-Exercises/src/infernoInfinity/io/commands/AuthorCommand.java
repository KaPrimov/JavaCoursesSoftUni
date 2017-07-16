package infernoInfinity.io.commands;

import infernoInfinity.annotations.WeaponInfo;
import infernoInfinity.io.interfaces.Writer;
import infernoInfinity.weapons.WeaponImpl;

public class AuthorCommand implements Command {

    private Writer outputWriter;

    public AuthorCommand(Writer outputWriter) {
        this.outputWriter = outputWriter;
    }

    @Override
    public void execute(String[] command) {
        WeaponInfo annotation = WeaponImpl.class.getAnnotation(WeaponInfo.class);
        outputWriter.print("Author: " + annotation.author());
    }
}
