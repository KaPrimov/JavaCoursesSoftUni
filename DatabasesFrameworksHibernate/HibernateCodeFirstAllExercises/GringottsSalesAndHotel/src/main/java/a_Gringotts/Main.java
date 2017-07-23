package a_Gringotts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager em = emf.createEntityManager();

        Wizard dumbledore = new Wizard();

        dumbledore.setFirstName("Albus");
        dumbledore.setLastName("Dumbledore");
        dumbledore.setAge(150);
        dumbledore.setMagicWandCreator("Pesho");
        dumbledore.setMagicWandSize((short) 15);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dumbledore.setDepositStartDate(
                LocalDateTime.parse("2017-10-20 10:30", dtf)
        );
        dumbledore.setExpirationDate(
                LocalDateTime.parse("2022-10-20 10:30", dtf)
        );
        dumbledore.setDepositAmount(2000.3123);
        dumbledore.setDepositCharge(0.2);
        dumbledore.setDepositExpired(false);
        em.getTransaction().begin();
        //em.persist(dumbledore);
        //em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
