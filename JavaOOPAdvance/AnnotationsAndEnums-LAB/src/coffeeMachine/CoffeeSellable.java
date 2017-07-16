package coffeeMachine;

public interface CoffeeSellable {
    void buyCoffee(String size, String type);
    void insertCoin(String coin);
    Iterable<Coffee> coffeesSold();
}
