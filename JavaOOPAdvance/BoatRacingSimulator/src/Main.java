import Core.CommandHandlerImpl;
import Core.Engine;
import contracts.BoatSimulatorController;
import controllers.BoatSimulatorControllerImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BoatSimulatorController controller = new BoatSimulatorControllerImpl();
        CommandHandlerImpl commandHandlerImpl = new CommandHandlerImpl(controller);
        Engine engine = new Engine(commandHandlerImpl);
        engine.Run();
    }
}
