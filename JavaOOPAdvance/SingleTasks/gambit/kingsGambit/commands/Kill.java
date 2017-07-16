package kingsGambit.commands;


import kingsGambit.contracts.Subject;

public class Kill extends Command {

    public Kill(String[] data, Subject attackableGroup) {
        super(attackableGroup);
        super.setData(data);
    }

    @Override
    public void execute() {
        String name = super.getData()[1];
        super.getKing().removeObserver(name);
    }
}
