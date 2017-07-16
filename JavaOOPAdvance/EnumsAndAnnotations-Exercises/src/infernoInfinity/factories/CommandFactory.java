package infernoInfinity.factories;

import infernoInfinity.io.commands.*;
import infernoInfinity.io.interfaces.Writer;
import infernoInfinity.repositories.WeaponRepository;

public class CommandFactory implements CommandFactorable {

    private WeaponRepository weaponRepository;
    private Writer outputWriter;

    public CommandFactory(WeaponRepository weaponRepository, Writer outputWriter) {
        this.weaponRepository = weaponRepository;
        this.outputWriter = outputWriter;
    }

    public void executeCommand(String[] tokens) {
        String type = tokens[0];
        Command command = null;
        switch (type) {
            case "Create":
                command = new CreateCommand(this.weaponRepository);
                break;
            case "Add":
                command = new AddCommand(this.weaponRepository);
                break;
            case "Remove":
                command = new RemoveCommand(this.weaponRepository);
                break;
            case "Print":
                command = new PrintCommand(this.weaponRepository, this.outputWriter);
                break;
            case "Compare":
                command = new CompareCommand(this.weaponRepository, this.outputWriter);
                break;
            case "Author":
                command = new AuthorCommand(this.outputWriter);
                break;
            case "Revision":
                command = new RevisionCommand(this.outputWriter);
                break;
            case "Description":
                command = new DescriptionCommand(this.outputWriter);
                break;
            case "Reviewers":
                command = new ReviewersCommand(this.outputWriter);
            default:
                break;
        }
        if (command != null) {
            command.execute(tokens);
        }
    }
}
