package core.io.commands;

import annotations.Alias;
import annotations.Inject;
import core.BlobRepository;

@Alias("pass")
public class PassCommand extends Command {

    @Inject
    private BlobRepository blobRepository;

    public PassCommand(String[] data) {
        super(data);
    }

    @Override
    public void execute(String[] params) {
        this.blobRepository.passTurn();
    }
}
