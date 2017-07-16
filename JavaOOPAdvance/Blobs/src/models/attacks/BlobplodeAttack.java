package models.attacks;

import interfaces.Attack;
import interfaces.Blobable;

public class BlobplodeAttack implements Attack {
    @Override
    public void execute(Blobable attacker, Blobable target) {
        int damage = attacker.getDamage() * 2;
       target.sufferAttack(damage);
       if(attacker.getHealth() - attacker.getHealth()/2 >= 1) {
           attacker.backfiredAttack(attacker.getHealth()/2);
       }
    }
}
