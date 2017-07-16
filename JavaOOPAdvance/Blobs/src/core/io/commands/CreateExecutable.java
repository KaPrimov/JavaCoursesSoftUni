package core.io.commands;

import annotations.Alias;
import annotations.Inject;
import core.BlobRepository;
import core.factories.AttackFactory;
import core.factories.BehaviorFactory;
import interfaces.Attack;
import interfaces.Behavior;
import models.Blob;

@Alias("create")
public class CreateExecutable extends Command{

    @Inject
    private BlobRepository blobRepository;
    @Inject
    private AttackFactory attackFactory;
    @Inject
    private BehaviorFactory behaviorFactory;

    public CreateExecutable(String[] data) {
        super(data);
    }

    @Override
    public void execute(String[] params) {
        String name = params[1];
        int health = Integer.parseInt(params[2]);
        int damage = Integer.parseInt(params[3]);
        Behavior behavior = this.behaviorFactory.returnObject(params[4]);
        Attack attack = this.attackFactory.returnObject(params[5]);
        blobRepository.addBlob(new Blob(name, health, damage, behavior, attack));
        this.blobRepository.passTurn();
    }
}
