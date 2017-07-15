package workForce.contracts;

import workForce.events.JobCompletedEvent;

public interface JobCompletedListener {
    void handleJobCompleted(JobCompletedEvent event);
}
