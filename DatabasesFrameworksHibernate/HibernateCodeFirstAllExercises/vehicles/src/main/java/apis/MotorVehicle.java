package apis;

public interface MotorVehicle extends Vehicle {

    int getNumberOfEngines();

    void setNumberOfEngines(int numberOfEngines);

    String getEngineType();

    void setEngineType(String engineType);

    byte getTankCapacity();

    void setTankCapacity(byte tankCapacity);
}
