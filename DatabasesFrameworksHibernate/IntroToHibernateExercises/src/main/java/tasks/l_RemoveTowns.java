package tasks;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@SuppressWarnings("unchecked")
public class l_RemoveTowns {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String town = reader.readLine();

        List<Address> addressesToDelete =
                em.createQuery("SELECT a FROM Address AS a " +
                "WHERE a.town.name = :townName")
                        .setParameter("townName", town)
                        .getResultList();

        em.getTransaction().begin();

        for (Address address : addressesToDelete) {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);

            }
            em.flush();
            em.remove(address);
        }
        Town townToDelete = (Town) em.createQuery("SELECT t FROM Town AS t " +
                "WHERE t.name = :townName")
                .setParameter("townName", town)
                .getSingleResult();

        em.remove(townToDelete);

        if (addressesToDelete.size() == 1) {
            System.out.printf("1 address in %s was deleted%n", town);
        } else {
            System.out.printf("%d addresses in %s were deleted%n", addressesToDelete.size(), town);
        }

        em.getTransaction().commit();

        em.close();
        emf.close();


    }
}
