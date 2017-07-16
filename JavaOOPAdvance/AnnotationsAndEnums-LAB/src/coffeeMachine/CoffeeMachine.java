package coffeeMachine;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine implements CoffeeSellable {

    private List<Coin> coins;
    private List<Coffee> coffeeList;

    public CoffeeMachine() {
        this.coffeeList = new ArrayList<>();
        this.coins = new ArrayList<>();
    }

    @Override
    public void buyCoffee(String size, String type) {
        CoffeeSize coffeeSize = CoffeeSize.valueOf(size.toUpperCase());
        CoffeeType coffeeType = CoffeeType.valueOf(type.toUpperCase());
        Coffee coffee = new Coffee(coffeeSize, coffeeType);
        int price = coffee.getPrice();
        int currentSum = this.coins.stream().mapToInt(Coin::getValue).sum();
        if(currentSum > price) {
            this.coffeeList.add(coffee);
            this.coins.clear();
        }
    }

    @Override
    public void insertCoin(String coinString) {
        Coin coin = Coin.valueOf(coinString.toUpperCase());
        this.coins.add(coin);
    }

    @Override
    public Iterable<Coffee> coffeesSold() {
        return this.coffeeList;
    }
}
