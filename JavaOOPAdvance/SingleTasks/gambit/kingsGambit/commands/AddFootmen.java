package kingsGambit.commands;

import kingsGambit.contracts.Subject;
import kingsGambit.models.Footman;

public class AddFootmen extends Command {

    public AddFootmen(String[] data, Subject king) {
        super(king);
        super.setData(data);
    }

    @Override
    public void execute() {
        for (String name : super.getData()) {
            super.getKing().addObserver(new Footman(name));
        }
    }
}
