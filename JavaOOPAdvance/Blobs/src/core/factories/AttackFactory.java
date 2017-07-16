package core.factories;

import interfaces.Attack;
import models.attacks.AbstractAttack;

import java.lang.reflect.Constructor;

public class AttackFactory {

    public Attack returnObject(String name) {
        String fullName = AbstractAttack.class.getName();
        int index = fullName.lastIndexOf('.') + 1;
        String packageName = fullName.substring(0, index);
        String attackName = packageName + name + "Attack";
        Attack attackInstance = null;
        try {
            Class<Attack> attackClass = (Class<Attack>) Class.forName(attackName);
            Constructor ctor = attackClass.getConstructor();
            attackInstance = (Attack) ctor.newInstance();

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return attackInstance;
    }

}
