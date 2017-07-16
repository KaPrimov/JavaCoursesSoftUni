package borderControl;

public class Robot implements Identifiable {

    private String model;
    private String idToDetain;

    public Robot(String model, String idToDetain) {
        this.model = model;
        this.idToDetain = idToDetain;
    }

    @Override
    public String getId() {
        return this.idToDetain;
    }
}
