package coffeeMachine;

public class Coffee {

    private CoffeeSize size;
    private CoffeeType type;
    private int price;

    public Coffee(CoffeeSize size, CoffeeType type) {
        this.size = size;
        this.type = type;
        this.price = size.getPrice();
    }

    public int getPrice() {
        return price;
    }
}
