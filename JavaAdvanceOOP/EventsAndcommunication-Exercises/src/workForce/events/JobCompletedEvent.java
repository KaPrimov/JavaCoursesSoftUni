package workForce.events;

import java.util.EventObject;

public class JobCompletedEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public JobCompletedEvent(Object source) {
        super(source);
    }
}
