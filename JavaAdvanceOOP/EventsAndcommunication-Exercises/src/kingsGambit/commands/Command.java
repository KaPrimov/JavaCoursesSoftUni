package kingsGambit.commands;

import kingsGambit.contracts.Executable;
import kingsGambit.groups.AttackableGroupImpl;

public abstract class Command implements Executable {

    private AttackableGroupImpl attackableGroup;
    private String[] data;

    protected Command(AttackableGroupImpl attackableGroup) {
        this.attackableGroup = attackableGroup;
    }

    protected AttackableGroupImpl getAttackableGroup() {
        return this.attackableGroup;
    }

    protected String[] getData() {
        return this.data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
