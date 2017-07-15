package tasks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class d_ContainsEmployees {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] fullName = reader.readLine().split("\\s+");

        Query query = em.createQuery(
                "SELECT e FROM Employee AS e WHERE e.firstName = :firstName AND e.lastName = :lastName"
        ).setParameter("firstName", fullName[0]).setParameter("lastName", fullName[1]);

        if (query.getResultList().size() > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        em.close();
        emf.close();
    }
}
