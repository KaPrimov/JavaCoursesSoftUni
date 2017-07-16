package militaryElite.models;

import militaryElite.interfaces.ILeutenantGeneral;
import militaryElite.interfaces.ISoldier;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class LeutenantGeneral extends Private implements ILeutenantGeneral {

    private Set<ISoldier> privates;

    public LeutenantGeneral(String id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new LinkedHashSet<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(returnListOfPrivates());
        return sb.toString();
    }

    @Override
    public void addPrivate(ISoldier soldier) {
        this.privates.add(soldier);
    }

    private StringBuilder returnListOfPrivates() {
        StringBuilder sb = new StringBuilder();
        sb.append("Privates:").append(System.lineSeparator());
        this.privates.forEach(p -> {
            sb.append("  " + p.toString());
        });
        return sb;
    }

    @Override
    public Set<ISoldier> getPrivates() {
        return Collections.unmodifiableSet(this.privates);
    }
}
