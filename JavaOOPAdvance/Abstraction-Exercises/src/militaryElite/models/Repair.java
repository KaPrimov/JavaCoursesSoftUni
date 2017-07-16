package militaryElite.models;

import militaryElite.interfaces.IRepair;

public class Repair implements IRepair {

    private String partName;
    private int hoursWorked;

    public Repair(String partName, int hoursWorked) {
        this.partName = partName;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getPartName() {
        return this.partName;
    }

    @Override
    public Integer getHoursWorked() {
        return this.hoursWorked;
    }

    @Override
    public String toString() {
        return String.format("  Part Name: %s Hours Worked: %d%n", this.partName, this.hoursWorked);
    }
}
