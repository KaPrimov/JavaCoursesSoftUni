package kingsGambit.models;

import kingsGambit.contracts.Observer;

public abstract class Soldier implements Observer {

    private String name;
    private boolean isDead;

    protected Soldier(String name) {
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }


}
