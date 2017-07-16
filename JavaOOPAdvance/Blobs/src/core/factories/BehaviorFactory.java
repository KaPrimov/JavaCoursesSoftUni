package core.factories;

import interfaces.Behavior;
import models.behavors.AbstractBehavior;

import java.lang.reflect.Constructor;

public class BehaviorFactory {

    public Behavior returnObject(String name) {
        String fullName = AbstractBehavior.class.getName();
        int index = fullName.lastIndexOf('.') + 1;
        String packageName = fullName.substring(0, index);
        String behaviorName = packageName + name + "Behavior";
        Behavior behaviorInstance = null;
        try {
            Class<Behavior> behaviorClass = (Class<Behavior>) Class.forName(behaviorName);
            Constructor ctor = behaviorClass.getConstructor();
            behaviorInstance = (Behavior) ctor.newInstance();

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return behaviorInstance;
    }
}
