package models.behavors;

import interfaces.Behavior;

public abstract class AbstractBehavior implements Behavior {

    private boolean isTriggered;

    public boolean isTriggered() {
        return this.isTriggered;
    }

    public void setIsTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }
}
