package PitFortress.main.interfaces;

import PitFortress.main.models.Mine;
import PitFortress.main.models.Player;

public interface IMine extends Comparable<Mine> {

    int getId();

    int getDelay();

    int getDamage();

    int getX();

    Player getPlayer();
}
