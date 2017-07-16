package militaryElite.models;

import militaryElite.interfaces.IEngineer;
import militaryElite.interfaces.IRepair;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Engineer extends SpecialisedSoldier implements IEngineer {

    private Set<IRepair> repairs;

    public Engineer(String id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary, corp);
        this.repairs = new LinkedHashSet<>();
    }

    @Override
    public void addRepair(IRepair repair) {
        this.repairs.add(repair);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Repairs:").append(System.lineSeparator());
        sb.append(returnRepairsString()).append(System.lineSeparator());
        return sb.toString();
    }

    private StringBuilder returnRepairsString() {
        StringBuilder sb = new StringBuilder();
        this.repairs.forEach(r -> {
            sb.append("  " + r.toString());
        });
        return sb;
    }

    @Override
    public Set<IRepair> getRepairs() {
        return Collections.unmodifiableSet(this.repairs);
    }
}
