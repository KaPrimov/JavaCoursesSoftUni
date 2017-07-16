package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Alias;

@Alias("fight")
public class FightCommand extends Command {

    public FightCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return "fight";
    }
}
