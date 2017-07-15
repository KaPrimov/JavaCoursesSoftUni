package tasks;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class c_RemoveObjects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        List<Town> towns = em.createQuery("SELECT t from Town t", Town.class).getResultList();

        em.getTransaction().begin();

        for (Town town : towns) {
            if (town.getName().length() > 5) {
                em.detach(town);
            } else {
                town.setName(town.getName().toLowerCase());
                em.persist(town);
            }
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
