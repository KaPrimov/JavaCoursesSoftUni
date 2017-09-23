package shoppingCenter;

import java.util.*;

public class ShoppingCenter {

    private static int itemId = 1;
    private Map<String, SortedSet<Product>> nameProducts;
    private Map<String, SortedSet<Product>> producersProducts;
    private Map<String, SortedSet<Product>> nameProducersProducts;
    private SortedMap<Double, SortedSet<Product>> priceProducts;

    public ShoppingCenter() {
        this.producersProducts = new HashMap<>();
        this.nameProducersProducts = new HashMap<>();
        this.priceProducts = new TreeMap<>();
        this.nameProducts = new HashMap<>();
    }

    public void addProduct(String name, double price, String producer) {

        Product product = new Product(name, price, producer, itemId);

        this.producersProducts.putIfAbsent(producer, new TreeSet<>());
        this.producersProducts.get(producer).add(product);

        this.nameProducersProducts.putIfAbsent(name + producer, new TreeSet<>());
        this.nameProducersProducts.get(name + producer).add(product);

        this.priceProducts.putIfAbsent(price, new TreeSet<>());
        this.priceProducts.get(price).add(product);

        this.nameProducts.putIfAbsent(name, new TreeSet<>());
        this.nameProducts.get(name).add(product);

        itemId++;
    }

    public int deleteProducts(String producer) {
        if (this.producersProducts.get(producer) == null) {
            return 0;
        }
        return this.producersProducts.remove(producer).size();
    }

    public int deleteProducts(String name, String producer) {
        if (this.nameProducersProducts.get(name + producer) == null) {
            return 0;
        }
        this.nameProducts.remove(name);
        this.producersProducts.remove(producer);
        return this.nameProducersProducts.remove(name + producer).size();
    }

    public Iterable<Product> findProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        if (this.nameProducts.get(name) == null) {
            return products;
        }

        products.addAll(this.nameProducts.get(name));

        return products;
    }

    public Iterable<Product> findProductsByProducer(String producer) {
        List<Product> products = new ArrayList<>();
        if (this.producersProducts.get(producer) == null) {
            return products;
        }

        products.addAll(this.producersProducts.get(producer));

        return products;
    }

    public Iterable<Product> findProductByPriceRange(double fromPrice, double toPrice) {
        List<Product> products = new ArrayList<>();
        for (SortedSet<Product> productSet : this.priceProducts.subMap(fromPrice, toPrice + 0.01).values()) {
           products.addAll(productSet);
        }
        return products;
    }
}
