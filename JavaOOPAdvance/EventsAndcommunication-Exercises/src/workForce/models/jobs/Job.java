package workForce.models.jobs;

import workForce.contracts.JobCompletedListener;
import workForce.events.JobCompletedEvent;
import workForce.models.employees.Employee;

import java.util.ArrayList;
import java.util.List;

public class Job {

    private int workedHours;
    private String name;
    private Employee employee;
    private List<JobCompletedListener> observers;

    public Job(String name, int workHoursRequired, Employee employee){
        this.setName(name);
        this.setWorkHoursRequired(workHoursRequired);
        this.setEmployee(employee);
        this.observers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private int getWorkHoursRequired() {
        return workedHours;
    }

    private void setWorkHoursRequired(int workHoursRequired) {
        this.workedHours = workHoursRequired;
    }

    private void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void addJobCompletedListener(JobCompletedListener listener){
        this.observers.add(listener);
    }

    public void update(){
        this.setWorkHoursRequired(this.getWorkHoursRequired() - this.employee.getHoursWorking());
        if(this.getWorkHoursRequired() <= 0){
            System.out.println(String.format("Job %s done!",this.getName()));
            this.fireJobCompletedEvent();
        }
    }

    private void fireJobCompletedEvent(){
        JobCompletedEvent event = new JobCompletedEvent(this);
        for (JobCompletedListener observer : observers) {
            observer.handleJobCompleted(event);
        }
    }

    @Override
    public String toString() {
        return String.format("Job: %s Hours Remaining: %d",this.getName(),this.getWorkHoursRequired());
    }
}
