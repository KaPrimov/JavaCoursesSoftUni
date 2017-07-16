package tasks;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unchecked")
public class k_IncreaseSalary {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')"
        ).getResultList();

        em.getTransaction().begin();
        for (Employee employee : employees) {
            BigDecimal newSalary = employee.getSalary().multiply(new BigDecimal("1.12"));
            employee.setSalary(newSalary);
            System.out.printf("%s %s ($%s)%n",employee.getFirstName(),employee.getLastName(),employee.getSalary());
        }
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
