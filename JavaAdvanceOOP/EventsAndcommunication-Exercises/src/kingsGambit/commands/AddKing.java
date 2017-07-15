package kingsGambit.commands;

import kingsGambit.groups.AttackableGroupImpl;
import kingsGambit.models.units.King;

public class AddKing extends Command  {


    public AddKing(String[] data, AttackableGroupImpl attackableGroup) {
        super(attackableGroup);
        super.setData(data);
    }

    @Override
    public void execute() {
        super.getAttackableGroup().addMember(new King(super.getData()[0]));
    }
}
