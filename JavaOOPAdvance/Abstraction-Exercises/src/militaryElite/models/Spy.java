package militaryElite.models;

import militaryElite.interfaces.ISpy;

public class Spy extends Soldier implements ISpy {
    private int codeNumber;

    public Spy(String id, String firstName, String lastName, int codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(System.lineSeparator());
        sb.append(String.format("Code Number: %s", this.codeNumber));
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public int getCodeNumber() {
        return this.codeNumber;
    }
}
