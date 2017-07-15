package eventImplementation.models;

import eventImplementation.contracts.NameChangeListener;

public class Handler implements NameChangeListener {
    @Override
    public void handleNameChange(NameChangeEvent event) {
        System.out.println("Dispatcher's name changed to " + event.getName() + ".");
    }
}
