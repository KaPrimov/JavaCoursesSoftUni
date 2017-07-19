package tasks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("unchecked")
public class n_MaxDepartmentSalary {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query queryMaxSalaries = em.createQuery("" +
                "SELECT d.name, max(e.salary) FROM Department AS d " +
                "INNER JOIN Employee AS e " +
                "ON e.department.id = d.id " +
                "GROUP BY d.name " +
                "HAVING max(e.salary) NOT BETWEEN 30000 AND 70000");

        List<Object[]> result = queryMaxSalaries.getResultList();

        for (Object[] data: result) {
            System.out.println(data[0] + " - " + data[1]);
        }

        em.close();
        emf.close();
    }
}
