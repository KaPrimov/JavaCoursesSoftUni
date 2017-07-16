package kingsGambit.commands;

import kingsGambit.contracts.Subject;
import kingsGambit.models.RoyalGuard;

public class AddRoyalGuard extends Command {

    public AddRoyalGuard(String[] data, Subject king) {
        super(king);
        super.setData(data);
    }

    @Override
    public void execute() {
        for (String name : super.getData()) {
            super.getKing().addObserver(new RoyalGuard(name));
        }
    }
}
