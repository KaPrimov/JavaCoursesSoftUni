package kingsGambit.commands;

import kingsGambit.groups.AttackableGroupImpl;
import kingsGambit.models.units.Footman;
import kingsGambit.models.units.Soldier;

public class AddFootmen extends Command {

    public AddFootmen(String[] data, AttackableGroupImpl attackableGroup) {
        super(attackableGroup);
        super.setData(data);
    }

    @Override
    public void execute() {
        for (String name : super.getData()) {
            Soldier footman = new Footman(name);
            footman.addObserver(super.getAttackableGroup());
            super.getAttackableGroup().addMember(footman);
        }
    }
}
