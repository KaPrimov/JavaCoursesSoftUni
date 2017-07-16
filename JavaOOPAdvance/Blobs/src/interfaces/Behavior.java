package interfaces;

public interface Behavior {

    void trigger(Blobable source);

    void applyRecurrentEffect(Blobable source);

    boolean isTriggered();

}
