import core.BlobRepository;
import core.factories.AttackFactory;
import core.factories.BehaviorFactory;
import core.io.CommandInterpreter;
import core.io.InputReader;
import core.io.OutputWriter;

public class Main {
    public static void main(String[] args) {
        BehaviorFactory behaviorFactory = new BehaviorFactory();
        AttackFactory attackFactory = new AttackFactory();
        BlobRepository blobRepository = new BlobRepository();
        OutputWriter outputWriter = new OutputWriter();
        CommandInterpreter commandInterpreter = new CommandInterpreter(blobRepository, outputWriter, attackFactory, behaviorFactory);
        InputReader inputReader = new InputReader(commandInterpreter, outputWriter);
        try {
            inputReader.readCommands();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
