package commands;

import models.Attacker;
import models.SubjectTarget;

public class TargetCommand implements Command {

    private Attacker attacker;
    private SubjectTarget target;

    public TargetCommand(Attacker attacker, SubjectTarget target) {
        this.attacker = attacker;
        this.target = target;
    }


    @Override
    public void execute() {
        this.attacker.setTarget(this.target);
    }
}
