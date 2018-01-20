package app;

public class Cake {
	private String name;
	private double price;
	public Cake(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + " Price: " + price;
	}	
}
