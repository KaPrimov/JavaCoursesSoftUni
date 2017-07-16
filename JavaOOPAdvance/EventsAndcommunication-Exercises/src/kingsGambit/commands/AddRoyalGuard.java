package kingsGambit.commands;

import kingsGambit.groups.AttackableGroupImpl;
import kingsGambit.models.units.RoyalGuard;
import kingsGambit.models.units.Soldier;

public class AddRoyalGuard extends Command {

    public AddRoyalGuard(String[] data, AttackableGroupImpl attackableGroup) {
        super(attackableGroup);
        super.setData(data);
    }

    @Override
    public void execute() {
        for (String name : super.getData()) {
            Soldier royalGuard = new RoyalGuard(name);
            royalGuard.addObserver(super.getAttackableGroup());
            super.getAttackableGroup().addMember(royalGuard);
        }
    }
}
