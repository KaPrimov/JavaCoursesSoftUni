package eventImplementation.models;

import eventImplementation.contracts.Dispatchable;
import eventImplementation.contracts.NameChangeListener;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher implements Dispatchable {

    private String name;
    private List<NameChangeListener> observers;

    public Dispatcher() {
        this.name = null;
        this.observers = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.fireNameChangeEvent();
    }


    @Override
    public void addNameChangeListener(NameChangeListener listener) {
        this.observers.add(listener);
    }

    @Override
    public void removeNameChangeListener(NameChangeListener listener) {
        this.observers.remove(listener);
    }

    @Override
    public void fireNameChangeEvent() {
        NameChangeEvent event = new NameChangeEvent(this, this.name);
        for (NameChangeListener observer : observers) {
            observer.handleNameChange(event);
        }
    }
}
