package kingsGambit.commands;

import kingsGambit.groups.AttackableGroupImpl;

public class Attack extends Command {

    public Attack(AttackableGroupImpl attackableGroup) {
        super(attackableGroup);
    }

    @Override
    public void execute() {
        super.getAttackableGroup().groupTakeAttack();
    }
}
