package infernoInfinity.io;

import infernoInfinity.io.interfaces.Writer;
import infernoInfinity.weapons.interfaces.Weapon;

import java.text.DecimalFormat;

public class OutputWriter implements Writer {
    @Override
    public void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }

    @Override
    public void printLevel(Weapon weapon) {
        DecimalFormat df = new DecimalFormat("0.0");
        String level = df.format(weapon.getLevel());
        System.out.println(weapon.toString() + String.format(" (Item Level: %s)", level));
    }
}
