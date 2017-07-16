package commands;

import groups.AttackGroup;
import models.SubjectTarget;

public class GroupTargetCommand implements Command {

    private AttackGroup attackGroup;
    private SubjectTarget target;

    public GroupTargetCommand(AttackGroup attackGroup, SubjectTarget target) {
        this.attackGroup = attackGroup;
        this.target = target;
    }

    @Override
    public void execute() {
        this.attackGroup.groupTarget(target);
    }
}
