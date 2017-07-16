package kingsGambit.models.units;

import kingsGambit.contracts.Atackable;
import kingsGambit.contracts.SoldierDeathListener;
import kingsGambit.contracts.Subjectable;
import kingsGambit.events.SoldierDeathEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class Soldier implements Atackable, Subjectable {

    private String name;
    private int hits;
    private List<SoldierDeathListener> observers;

    protected Soldier(String name, int hits) {
        this.name = name;
        this.hits = hits;
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(SoldierDeathListener observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(SoldierDeathListener observer) {
        this.observers.remove(observer);
    }

    public final String getName() {
        return this.name;
    }

    @Override
    public void takeAHit() {
        this.hits--;
        if (this.hits == 0) {
            this.fireObservers();
        }
    }

    private void fireObservers() {
        SoldierDeathEvent soldierDeathEvent = new SoldierDeathEvent(this, this.name);
        for (SoldierDeathListener observer : observers) {
            observer.handleSoldierDeath(soldierDeathEvent);
        }
    }
}
