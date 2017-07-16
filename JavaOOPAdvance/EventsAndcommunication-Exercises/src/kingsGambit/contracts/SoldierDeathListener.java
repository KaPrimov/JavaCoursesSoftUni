package kingsGambit.contracts;

import kingsGambit.events.SoldierDeathEvent;

public interface SoldierDeathListener {

    void handleSoldierDeath(SoldierDeathEvent event);

}
