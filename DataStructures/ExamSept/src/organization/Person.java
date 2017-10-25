package organization;
public class Person {

    private String name;

    private double salary;

    public Person(String name, double salary) {
        this.setName(name);
        this.setSalary(salary);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
