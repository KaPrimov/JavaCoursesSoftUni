package militaryElite.models;

import militaryElite.interfaces.IMission;

public class Mission implements IMission {

    private String codeName;
    private String state;

    public Mission(String codeName, String state) {
        this.codeName = codeName;
        setState(state);
    }

    public void completeMission() {
        this.state = "Finished";
    }

    @Override
    public String getCodeName() {
        return this.codeName;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return String.format("  Code Name: %s State: %s%n", this.codeName, this.state);
    }

    public void setState(String state) {
        this.state = state;
    }
}
