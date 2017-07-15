package CatLady;

public class Siamese extends Cat {
    private String earSize;

    public Siamese(String name, String earSize) {
        super(name);
        this.earSize = earSize;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.getClass().getSimpleName(), super.getName(), this.earSize);
    }
}
