package models.behavors;

import interfaces.Blobable;

public class InflatedBehavior extends AbstractBehavior {

    private static final int HEALTH_BONUS = 50;
    private static final int HEALTH_DECREASE = -10;

    @Override
    public void trigger(Blobable source) {
        this.setIsTriggered(true);
        source.updateHealth(HEALTH_BONUS);
    }

    @Override
    public void applyRecurrentEffect(Blobable source) {
        if(this.isTriggered()) {
            source.updateHealth(HEALTH_DECREASE);
        }
    }
}
