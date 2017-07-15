package CatLady;

public class Cymric extends Cat {
    private String length;

    public Cymric(String name, String length) {
        super(name);
        this.length = length;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.getClass().getSimpleName(), super.getName(), this.length);
    }
}
