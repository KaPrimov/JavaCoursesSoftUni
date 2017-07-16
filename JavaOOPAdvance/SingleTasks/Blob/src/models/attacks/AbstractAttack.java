package models.attacks;

import interfaces.Attack;
import interfaces.Blobable;

public abstract class AbstractAttack implements Attack {
    public abstract void execute(Blobable attacker, Blobable target);
}
