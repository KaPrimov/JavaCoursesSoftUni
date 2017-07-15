package kingsGambit.contracts;

public interface Subjectable {

    void addObserver(SoldierDeathListener observer);
    void removeObserver(SoldierDeathListener observer);

}
