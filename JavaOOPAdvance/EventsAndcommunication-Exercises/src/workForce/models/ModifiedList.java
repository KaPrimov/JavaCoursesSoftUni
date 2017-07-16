package workForce.models;

import workForce.contracts.JobCompletedListener;
import workForce.events.JobCompletedEvent;

import java.util.ArrayList;

public class ModifiedList<T> extends ArrayList<T> implements JobCompletedListener {

    @Override
    public void handleJobCompleted(JobCompletedEvent event) {
        this.remove(event.getSource());
    }
}
