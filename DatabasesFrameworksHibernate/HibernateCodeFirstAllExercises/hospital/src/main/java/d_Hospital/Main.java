package d_Hospital;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        LocalDate localDate = LocalDate.parse(
                "1990-10-08",
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );
        Patient gosho = new Patient();
        gosho.setFirstName("Gosho");
        gosho.setLastName("Goshov");
        gosho.setAddress("Secret Lair");
        gosho.setDateOFBirth(localDate);
        em.persist(gosho);
        Medicament medicament1 = new Medicament();
        medicament1.setName("Aspirin");
        medicament1.setPatients(new HashSet<>(Arrays.asList(gosho)));
        Medicament medicament2 = new Medicament();
        medicament2.setName("Fervex");
        medicament2.setPatients(new HashSet<>(Arrays.asList(gosho)));
        em.persist(medicament1);
        em.persist(medicament2);

        Diagnose diagnose = new Diagnose();
        diagnose.setComments("Fooling around");
        diagnose.setName("some name");
        diagnose.setPatients(new HashSet<>(Arrays.asList(gosho)));
        Diagnose diagnose2 = new Diagnose();
        diagnose2.setComments("Fooling around 2");
        diagnose2.setName("some name 2");
        diagnose2.setPatients(new HashSet<>(Arrays.asList(gosho)));
        em.persist(diagnose);
        em.persist(diagnose2);

        Visitation visitation = new Visitation();
        visitation.setComments("Nice chat");
        visitation.setDate(LocalDate.now());
        em.persist(visitation);


        em.getTransaction().commit();

        em.close();
        emf.close();



    }
}
