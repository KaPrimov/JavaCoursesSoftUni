package eventImplementation.models;

import java.util.EventObject;

public class NameChangeEvent extends EventObject {

    private String name;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public NameChangeEvent(Object source, String newName) {
        super(source);
        this.name = newName;
    }

    public String getName() {
        return name;
    }
}
