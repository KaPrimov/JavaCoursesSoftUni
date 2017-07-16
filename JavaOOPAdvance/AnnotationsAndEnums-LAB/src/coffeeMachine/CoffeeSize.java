package coffeeMachine;

public enum  CoffeeSize {
    SMALL(50, 50), NORMAL(100, 75), DOUBLE(200, 100);

    private int ml;
    private int c;

    CoffeeSize(int ml, int c) {
        this.ml = ml;
        this.c = c;
    }

    public int getMl() {
        return ml;
    }

    public int getPrice() {
        return c;
    }


    @Override
    public String toString() {
        return String.format("%s - %s", this.ml, this.c);
    }
}
