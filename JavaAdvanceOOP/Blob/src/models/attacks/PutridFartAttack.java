package models.attacks;

import interfaces.Attack;
import interfaces.Blobable;

public class PutridFartAttack implements Attack {

    @Override
    public void execute(Blobable source, Blobable target) {
        target.sufferAttack(source.getDamage());
    }
}
