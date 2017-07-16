package kingsGambit.models;

import kingsGambit.contracts.Observer;
import kingsGambit.contracts.Subject;

import java.util.LinkedHashMap;
import java.util.Map;

public class King implements Subject {

    private String name;
    private Map<String, Observer> observers;

    public King(String name) {
        this.observers = new LinkedHashMap<>();
        this.name = name;
    }


    @Override
    public void addObserver(Observer observer) {
        this.observers.putIfAbsent(observer.getName(), observer);
    }

    @Override
    public void removeObserver(String observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        System.out.println(String.format("King %s is under attack!", this.name));

        for (Observer observer : observers.values()) {
            observer.takeAction();
        }
    }
}
