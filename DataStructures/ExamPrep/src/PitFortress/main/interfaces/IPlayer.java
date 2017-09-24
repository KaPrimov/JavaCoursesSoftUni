package PitFortress.main.interfaces;

import PitFortress.main.models.Player;

public interface IPlayer extends Comparable<Player> {

    String getName();

    int getRadius();

    int getScore();
}
