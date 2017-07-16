package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Alias;
import pr0304Barracks.contracts.Inject;
import pr0304Barracks.contracts.Repository;

@Alias("retire")
public class RetireCommand extends Command {

    @Inject
    private Repository repository;


    public RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = super.getData()[1];
        try {
            this.repository.removeUnit(unitType);
        } catch (IllegalArgumentException iae) {
            return iae.getMessage();
        }
        return unitType + " retired!";
    }
}
