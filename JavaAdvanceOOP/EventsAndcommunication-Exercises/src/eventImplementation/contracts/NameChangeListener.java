package eventImplementation.contracts;

import eventImplementation.models.NameChangeEvent;

public interface NameChangeListener {

    void handleNameChange(NameChangeEvent event);

}
