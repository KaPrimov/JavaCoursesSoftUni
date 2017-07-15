package kingsGambit.commands;

import kingsGambit.groups.AttackableGroupImpl;

public class Kill extends Command {

    public Kill(String[] data, AttackableGroupImpl attackableGroup) {
        super(attackableGroup);
        super.setData(data);
    }

    @Override
    public void execute() {
        String name = super.getData()[1];
        super.getAttackableGroup().returnObject(name).takeAHit();
    }
}
