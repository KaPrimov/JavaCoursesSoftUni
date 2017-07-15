package kingsGambit.groups;

import kingsGambit.contracts.Atackable;
import kingsGambit.contracts.AttackableGroup;
import kingsGambit.contracts.SoldierDeathListener;
import kingsGambit.events.SoldierDeathEvent;

import java.util.LinkedHashMap;

public class AttackableGroupImpl<T extends Atackable> extends LinkedHashMap<String, T> implements SoldierDeathListener, AttackableGroup<T> {
    @Override
    public void handleSoldierDeath(SoldierDeathEvent event) {
        this.remove(event.getName());
    }

    @Override
    public void addMember(T atackable) {
        this.put(atackable.getName(), atackable);
    }

    @Override
    public void groupTakeAttack() {
        for (T t : this.values()) {
            t.respondToAttack();
        }
    }

    public T returnObject(String name) {
        return this.get(name);
    }

}
