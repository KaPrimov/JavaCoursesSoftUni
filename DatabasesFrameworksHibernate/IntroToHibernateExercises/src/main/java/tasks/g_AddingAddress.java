package tasks;

import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class g_AddingAddress {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Address address = new Address();
        address.setText("Vitoshka 15");

        em.getTransaction().begin();
        em.persist(address);

        Query selectNakov = em.createQuery("select e from Employee as e " +
                "where e.lastName = 'Nakov'");

        List<Employee> nakovs = selectNakov.getResultList();

        for (Employee nakov : nakovs) {
            nakov.setAddress(address);
            em.persist(nakov);
            System.out.println(nakov.getAddress().getText());
        }

        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}
