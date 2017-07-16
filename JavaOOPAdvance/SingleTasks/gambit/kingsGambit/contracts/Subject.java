package kingsGambit.contracts;

public interface Subject {

    void addObserver(Observer observer);
    void removeObserver(String observer);
    void notifyObservers();

}
