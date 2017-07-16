package kingsGambit.models.units;

public class Footman extends Soldier {

    public Footman(String name) {
        super(name, 2);
    }

    @Override
    public void respondToAttack() {
        System.out.println(String.format("Footman %s is panicking!", super.getName()));
    }
}
