package core.io.commands;

import annotations.Alias;
import annotations.Inject;
import core.BlobRepository;
import core.io.OutputWriter;
import interfaces.Blobable;

@Alias("status")
public class StatusCommand extends Command {

    @Inject
    private OutputWriter outputWriter;

    @Inject
    private BlobRepository blobRepository;

    public StatusCommand(String[] data) {
        super(data);
    }

    @Override
    public void execute(String[] params) {
        for (Blobable blobable : blobRepository.returnAllBlobs()) {
            outputWriter.writeMessage(blobable.toString());
        }
        this.blobRepository.passTurn();
    }
}
