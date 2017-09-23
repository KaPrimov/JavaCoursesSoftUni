package shoppingCenter;

public class Product implements Comparable<Product> {
    private String name;
    private double price;
    private String producer;
    private int id;

    public Product(String name, double price, String producer, int id) {
        this.name = name;
        this.price = price;
        this.producer = producer;
        this.id = id;
    }


    @Override
    public int compareTo(Product other) {

        int index = this.name.compareTo(other.name);
        if(index == 0) {
            index = Double.compare(this.price, other.price);
        }
        if(index == 0) {
            index = this.producer.compareTo(other.producer);
        }

        if(index == 0) {
            index = Integer.compare(this.id, other.id);
        }

        return index;
    }

    @Override
    public String toString() {
        return "{" + this.name + ";" + this.producer + ";" + String.format("%.2f",  this.price) + "}";
    }
}
