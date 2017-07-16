package workForce.models.employees;

import workForce.models.jobs.Job;

public abstract class Employee {

    private String name;
    private int hoursWorking;
    private Job job;

    public Employee(String name, int hoursWorking) {
        this.name = name;
        this.hoursWorking = hoursWorking;
    }

    public int getHoursWorking() {
        return this.hoursWorking;
    }
}
