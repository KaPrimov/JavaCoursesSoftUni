package kingsGambit.models;

import kingsGambit.contracts.SoldierDeathListener;
import kingsGambit.events.SoldierDeathEvent;

import java.util.LinkedList;

public class ModifiedList<T> extends LinkedList<T> implements SoldierDeathListener{

    @Override
    public void handleSoldierDeath(SoldierDeathEvent event) {
        this.remove(event.getSource());
    }
}
