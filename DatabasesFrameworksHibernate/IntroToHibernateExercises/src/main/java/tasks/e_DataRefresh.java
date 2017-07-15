package tasks;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class e_DataRefresh {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Employee luckyOne = (Employee) em.createQuery("SELECT e FROM Employee AS e WHERE e.id=4").getSingleResult();

        luckyOne.setFirstName(luckyOne.getFirstName().toUpperCase());

        //em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(luckyOne);
        em.getTransaction().commit();

        System.out.println(luckyOne.getFirstName());
        em.close();
        emf.close();
    }
}
