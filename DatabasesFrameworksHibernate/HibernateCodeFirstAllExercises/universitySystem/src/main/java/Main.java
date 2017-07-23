import entities.api.Course;
import entities.impl.CourseImpl;
import entities.impl.StudentImpl;
import entities.impl.TeacherImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirst");
        EntityManager em = emf.createEntityManager();


        StudentImpl student = new StudentImpl("Pesho", "Peshov", "123123", 5.23, "Good");
        TeacherImpl teacher = new TeacherImpl("Bate", "Gosho", "213123", "b@b.bg", 21.21);
        Course course = new CourseImpl();
        course.setTeacher(teacher);
        course.setStudents(new HashSet<>(Collections.singletonList(student)));
        course.setName("Java DB");

        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
