package kingsGambit.events;

import kingsGambit.models.units.Soldier;

import java.util.EventObject;

public class SoldierDeathEvent extends EventObject {

    private String name;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SoldierDeathEvent(Soldier source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
