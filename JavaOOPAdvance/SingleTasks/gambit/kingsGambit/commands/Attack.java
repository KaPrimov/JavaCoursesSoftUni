package kingsGambit.commands;

import kingsGambit.contracts.Subject;

public class Attack extends Command {

    public Attack(Subject attackableGroup) {
        super(attackableGroup);
    }

    @Override
    public void execute() {
        super.getKing().notifyObservers();
    }
}
