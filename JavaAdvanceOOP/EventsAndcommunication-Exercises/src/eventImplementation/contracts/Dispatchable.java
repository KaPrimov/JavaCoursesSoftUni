package eventImplementation.contracts;

public interface Dispatchable {

    void addNameChangeListener(NameChangeListener listener);
    void removeNameChangeListener(NameChangeListener listener);
    void fireNameChangeEvent();
    String getName();
    void setName(String name);
}
