package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Alias;
import pr0304Barracks.contracts.Inject;
import pr0304Barracks.contracts.Repository;

@Alias("report")
public class ReportCommand extends Command {

    @Inject
    private Repository repository;

    public ReportCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String output = this.repository.getStatistics();
        return output;
    }
}
