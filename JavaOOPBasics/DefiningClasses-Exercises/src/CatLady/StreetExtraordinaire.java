package CatLady;

public class StreetExtraordinaire extends Cat {
    private String decibels;

    public StreetExtraordinaire(String name, String decibels) {
        super(name);
        this.decibels = decibels;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.getClass().getSimpleName(), super.getName(), this.decibels);
    }
}
