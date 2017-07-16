package core.io.commands;

import annotations.Alias;
import annotations.Inject;
import core.BlobRepository;
import interfaces.Blobable;

@Alias("attack")
public class AttackCommand extends Command {


    @Inject
    private BlobRepository blobRepository;

    public AttackCommand(String[] data) {
        super(data);
    }

    @Override
    public void execute(String[] params) {
        Blobable attacker = blobRepository.returnBlob(params[1]);
        Blobable target = blobRepository.returnBlob(params[2]);
        attacker.attack(attacker, target);
        this.blobRepository.passTurn();
    }
}
