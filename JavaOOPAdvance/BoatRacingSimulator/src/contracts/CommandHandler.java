package contracts;

import exeptions.*;

import java.util.List;

public interface CommandHandler {
    String executeCommand(String name, List<String> parameters) throws DuplicateModelException, NonExistantModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException;
}
