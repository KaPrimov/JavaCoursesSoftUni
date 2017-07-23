package b_Sales;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager em = emf.createEntityManager();

        Product product = new Product("Chocolate", 20, new BigDecimal("40"));
        Customer customer = new Customer("Pesho", "Pesho@Peshov.com", "2314213412");
        Location sofia = new Location("Sofia");

        Sale sale = new Sale(product, customer, sofia, LocalDateTime.now());
        Sale sale1 = new Sale(product, customer, sofia, LocalDateTime.now());
        em.getTransaction().begin();
        //em.persist(sale);
        //em.persist(sale1);
        //em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
