package sweepAndPrune;

public class GameObject extends Rectangle {

    private final String name;

    public GameObject(double x1, double y1, String name) {
        super(x1, y1);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

