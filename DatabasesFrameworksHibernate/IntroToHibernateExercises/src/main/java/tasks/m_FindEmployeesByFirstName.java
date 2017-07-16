package tasks;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;

public class m_FindEmployeesByFirstName {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String initials = reader.readLine();
        initials = initials.charAt(0) + "" + Character.toLowerCase(initials.charAt(1));

        Query employeesQuery = em.createQuery("SELECT e FROM Employee AS e " +
                "WHERE substring(e.firstName,  1, 2) = :initials").setParameter("initials", initials);

        List<Employee> employees = employeesQuery.getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s - %s - (%s)%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getJobTitle(),
                    new DecimalFormat("0.00").format(employee.getSalary()));
        }
    }
}
