package tasks;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class f_EmployeeFromSeattle {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        //Step 1
        Query richEmployeesQuery = em.createQuery("SELECT e.firstName FROM Employee AS e WHERE e.salary > 50000");
        List<String> names = richEmployeesQuery.getResultList();

        for (String name : names) {
            System.out.println(name);
        }

        //Step 2
        Query rAndDEmployees = em.createQuery("SELECT e FROM Employee AS e " +
                "JOIN Department AS d " +
                "ON d.id = e.department.id " +
                "where d.name = 'Research and Development' ");

        List<Employee> employees = rAndDEmployees.getResultList();

        employees.stream()
                .sorted((a, b) -> {
                    int index = a.getSalary().compareTo(b.getSalary());
                    if (index == 0) {
                        index = b.getFirstName().compareTo(a.getFirstName());
                    }

                    return index;
                }). forEach(employee -> {
            System.out.printf("%s %s from %s - $%.2f%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        });

        em.close();
        emf.close();

    }
}
