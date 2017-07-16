package commands;

import groups.AttackGroup;

public class GroupAttackCommand implements Command {

    private AttackGroup attackGroup;

    public GroupAttackCommand(AttackGroup attackGroup) {
        this.attackGroup = attackGroup;
    }

    @Override
    public void execute() {
        this.attackGroup.groupAttack();
    }
}
