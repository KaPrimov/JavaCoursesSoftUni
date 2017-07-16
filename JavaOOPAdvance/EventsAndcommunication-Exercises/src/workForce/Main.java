package workForce;

import workForce.models.ModifiedList;
import workForce.models.employees.Employee;
import workForce.models.employees.PartTimeEmployee;
import workForce.models.employees.StandartEmployee;
import workForce.models.jobs.Job;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ModifiedList<Job> jobs = new ModifiedList<>();
        Map<String, Employee> employeesByName = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        while (!line.equals("End")) {
            String[] parameters = line.split(" ");
            switch (parameters[0]) {
                case "Job": {
                    Employee employeeForJob = employeesByName.get(parameters[3]);
                    Job newJob = new Job(parameters[1], Integer.parseInt(parameters[2]), employeeForJob);
                    newJob.addJobCompletedListener(jobs);
                    jobs.add(newJob);
                    break;
                }

                case "StandartEmployee": {
                    Employee newStandartEmployee = new StandartEmployee(parameters[1]);
                    employeesByName.put(parameters[1], newStandartEmployee);
                    break;
                }
                case "PartTimeEmployee": {
                    Employee newPartTimeEmployee = new PartTimeEmployee(parameters[1]);
                    employeesByName.put(parameters[1], newPartTimeEmployee);
                    break;
                }
                case "Pass": {
                    Job[] jobsToUpdate = new Job[jobs.size()];
                    jobs.toArray(jobsToUpdate);
                    for (Job job : jobsToUpdate) {
                        job.update();
                    }
                    break;
                }
                case "Status": {
                    for (Job job : jobs) {
                        System.out.println(job);
                    }
                }
            }
            line = scanner.nextLine();
        }
    }
}
