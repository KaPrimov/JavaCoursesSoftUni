package P04_DetailPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailsPrinter {

    private Iterable<Employee> employees;

    public DetailsPrinter(Iterable<Employee> employees) {
        this.employees = employees;
    }

    public void printDetails() {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public static void main(String[] args) {
        Employee employee = new Employee("empl");

        Manager manager = new Manager("Gosho", Arrays.asList("One", "Two"));

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(manager);

        DetailsPrinter detailsPrinter = new DetailsPrinter(employees);

        detailsPrinter.printDetails();
    }
}
