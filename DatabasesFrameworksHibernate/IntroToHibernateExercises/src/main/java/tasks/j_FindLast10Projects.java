package tasks;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class j_FindLast10Projects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        List<Project> projects = em
                .createQuery("SELECT p FROM Project AS p " +
                        "ORDER BY p.startDate DESC ")
                .setMaxResults(10)
                .getResultList();

        projects.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.printf("%s | %s | %s | %s | %n",
                            p.getName(),
                            p.getDescription(),
                            findDate(p.getStartDate()) ,
                            findDate(p.getEndDate()));
                });
    }

    private static String findDate(Timestamp date) {
        return date == null ? "(NULL)" : new SimpleDateFormat("yyyy/MM/d").format(date);
    }
}
