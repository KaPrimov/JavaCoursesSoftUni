package apis;

public interface Vehicle {

    long getId();

    void setId(long id);

    String getManufacturer();

    void setManufacturer(String manufacturer);

    String getModel();

    void setModel(String model);

    double getPrice();

    void setPrice(double price);

    int getMaxSpeed();

    void setMaxSpeed(int maxSpeed);
}
