package militaryElite;

import militaryElite.interfaces.ISoldier;

import militaryElite.interfaces.*;
import militaryElite.models.*;

import java.util.HashMap;

public class CommandInterpretator {
    private HashMap<String, ISoldier> privatesById;

    public CommandInterpretator() {
        this.privatesById = new HashMap<>();
    }

    public void createSoldier(String[] params) {
        String type = params[0];
        String id = params[1];
        String firstName = params[2];
        String lastName = params[3];
        Double salary;
        String corp;

        switch (type) {
            case "Private":
                salary = Double.valueOf(params[4]);
                ISoldier soldier = new Private(id, firstName, lastName, salary);
                this.privatesById.put(id, soldier);
                System.out.println(soldier.toString().trim());
                break;

            case "Spy":
                int codeNumber = Integer.parseInt(params[4]);
                ISoldier spy = new Spy(id, firstName, lastName, codeNumber);
                System.out.println(spy.toString().trim());
                break;

            case "LeutenantGeneral":
                salary = Double.valueOf(params[4]);
                ILeutenantGeneral general = new LeutenantGeneral(id, firstName, lastName, salary);
                for (int i = 5; i < params.length; i++) {
                    general.addPrivate(this.privatesById.get(params[i]));
                }
                System.out.println(general.toString().trim());
                break;

            case "Engineer":
                salary = Double.valueOf(params[4]);
                corp = params[5];
                IEngineer engineer = new Engineer(id, firstName, lastName, salary, corp);

                for (int i = 6; i < params.length; i += 2) {
                    String repairName = params[i];
                    int hours = Integer.valueOf(params[i + 1]);
                    IRepair repair = new Repair(repairName, hours);
                    engineer.addRepair(repair);
                }
                System.out.println(engineer.toString().trim());
                break;

            case "Commando":
                salary = Double.valueOf(params[4]);
                corp = params[5];
                ICommando commando = new Commando(id, firstName, lastName, salary, corp);

                for (int i = 6; i < params.length; i += 2) {
                    String missionName = params[i];
                    try{
                        String state = params[i + 1];
                        Mission mission = new Mission(missionName, state);
                        commando.addMission(mission);

                    } catch (IllegalArgumentException ex){
                    }
                }
                System.out.println(commando.toString().trim());
                break;

            default:
                break;
        }

    }
}