package tasks;


import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

public class i_ConcurrentChanges {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();

        Employee employee = (Employee) em.createQuery("SELECT e FROM Employee AS e WHERE e.id = 20").getSingleResult();

        em.getTransaction().begin();
        em2.getTransaction().begin();
        employee.setFirstName("Hank");
        employee.setLastName("Moody");

        em.lock(em, LockModeType.PESSIMISTIC_WRITE);
        em2.lock(em, LockModeType.PESSIMISTIC_WRITE);

        em.getTransaction().commit();
        em2.getTransaction().commit();

        em.close();
        em2.close();
        emf.close();
    }
}
