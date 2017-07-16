package models.behavors;

import interfaces.Blobable;

public class AggressiveBehavior extends AbstractBehavior {

    private static final int AGGRESSIVE_DAMAGE_MULTIPLY = 2;
    private static final int AGGRESSIVE_DAMAGE_DECREMENT = -5;

    @Override
    public void trigger(Blobable source) {
        super.setIsTriggered(true);
        this.applyTriggerEffect(source);
    }

    @Override
    public void applyRecurrentEffect(Blobable source) {
        if(this.isTriggered()) {
            source.updateDamage(source.getDamage() + AGGRESSIVE_DAMAGE_DECREMENT);
        }
    }

    private void applyTriggerEffect(Blobable source) {
        source.updateDamage(source.getDamage() * AGGRESSIVE_DAMAGE_MULTIPLY);
    }
}
