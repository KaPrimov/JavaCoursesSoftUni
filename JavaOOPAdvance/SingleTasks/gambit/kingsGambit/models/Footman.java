package kingsGambit.models;

public class Footman extends Soldier {

    public Footman(String name) {
        super(name);
    }

    @Override
    public void takeAction() {
        System.out.println(String.format("Footman %s is panicking!", super.getName()));

    }
}
