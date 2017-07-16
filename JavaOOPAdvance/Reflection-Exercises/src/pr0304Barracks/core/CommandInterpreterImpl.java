package pr0304Barracks.core;

import pr0304Barracks.contracts.*;
import pr0304Barracks.core.commands.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandInterpreterImpl implements CommandInterpreter {


    private static final String COMMANDS_FILE_PATH = "./src/pr0304Barracks/core/commands";
    private static final String COMMAND_PACKAGE = "pr0304Barracks.core.commands." ;

    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {
        char firstChar = Character.toUpperCase(commandName.charAt(0));
        String fullName = Command.class.getName();
        int index = fullName.lastIndexOf('.') + 1;
        String packageName = fullName.substring(0, index);

        commandName = packageName + firstChar + commandName.substring(1) + "Command";
        Command commandInstance = null;
        try {
            Class<Command> commandClass = (Class<Command>) Class.forName(commandName);
            Constructor ctor = commandClass.getConstructor(String[].class);
            commandInstance = (Command) ctor.newInstance((Object) data);
            this.injectDependencies(commandInstance, commandClass);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return commandInstance;
    }

    private void injectDependencies(Executable commandInstance, Class<Command> commandClass) throws ReflectiveOperationException {
        Field[] cmdFields = commandClass.getDeclaredFields();
        Field[] theseFields = CommandInterpreterImpl.class.getDeclaredFields();

        for (Field exeField : cmdFields) {
            if(!exeField.isAnnotationPresent(Inject.class)) {
                continue;
            }
            exeField.setAccessible(true);
            for (Field thisField : theseFields) {
                if(!thisField.getType().equals(exeField.getType())) {
                    continue;
                }
                thisField.setAccessible(true);
                exeField.set(commandInstance, thisField.get(this));
            }
        }
    }
}
