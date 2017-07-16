package infernoInfinity.io.commands;

import infernoInfinity.annotations.WeaponInfo;
import infernoInfinity.io.interfaces.Writer;
import infernoInfinity.weapons.WeaponImpl;

import java.util.Arrays;

public class ReviewersCommand implements Command {

    private Writer outputWriter;

    public ReviewersCommand(Writer outputWriter) {
        this.outputWriter = outputWriter;
    }

    @Override
    public void execute(String[] command) {
        WeaponInfo annotation = WeaponImpl.class.getAnnotation(WeaponInfo.class);
        outputWriter.print("Reviewers: " + Arrays.toString(annotation.reviewers()).replace("[", "").replace("]", ""));
    }

}
