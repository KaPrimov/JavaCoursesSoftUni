package carShop;

public interface Rentable extends Car {
    Integer getMinRentDays();

    Double getPricePerDay();
}
