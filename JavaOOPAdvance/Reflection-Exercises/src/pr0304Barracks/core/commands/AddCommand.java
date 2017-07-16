package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Alias;
import pr0304Barracks.contracts.Inject;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;

@Alias("add")
public class AddCommand extends Command {

    @Inject
    private Repository repository;

    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = super.getData()[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }
}
