package militaryElite.models;

import militaryElite.interfaces.ICommando;
import militaryElite.interfaces.IMission;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Commando extends SpecialisedSoldier implements ICommando {

    Set<IMission> missions;

    public Commando(String id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary, corp);
        this.missions = new LinkedHashSet<>();
    }

    @Override
    public void addMission(IMission mission) {
        if(mission.getState().equals("Finished") || mission.getState().equals("inProgress")) {
            this.missions.add(mission);
        }
    }

    private StringBuilder returnRepairsString() {
        StringBuilder sb = new StringBuilder();
        this.missions.forEach(m -> {
            sb.append("  " + m.toString());
        });
        return sb;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Missions:").append(System.lineSeparator());
        sb.append(returnRepairsString()).append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public Set<IMission> getMissions() {
        return Collections.unmodifiableSet(this.missions);
    }
}
