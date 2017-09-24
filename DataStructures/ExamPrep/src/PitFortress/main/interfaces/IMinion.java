package PitFortress.main.interfaces;

import PitFortress.main.models.Minion;

public interface IMinion extends Comparable<Minion> {

    int getId();

    int getX();

    int getHealth();
}
