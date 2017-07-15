package tasks;

import entities.Address;
import entities.Department;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;

public class b_CreateObjects {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Employee manager = (Employee) em.createQuery("SELECT em FROM Employee AS em WHERE em.id=1").getSingleResult();

        Department training = new Department();
        training.setName("Training");
        training.setManager(manager);
        em.persist(training);

        Town burgas = new Town();
        burgas.setName("Burgas");
        em.persist(burgas);

        Address address1 = new Address();
        address1.setText("Morskata Gradina");
        address1.setTown(burgas);
        em.persist(address1);

        Address address2 = new Address();
        address2.setText("Downtown");
        address2.setTown(burgas);
        em.persist(address2);

        Employee employee = new Employee();
        employee.setFirstName("Bruce");
        employee.setLastName("Wayne");
        employee.setAddress(address1);
        employee.setHireDate(new Timestamp(20171020));
        employee.setJobTitle("Manager");
        employee.setMiddleName("W.");
        employee.setDepartment(training);
        em.persist(employee);

        Employee employee1 = new Employee();
        employee1.setFirstName("Petran");
        employee1.setLastName("Petranov");
        employee1.setAddress(address2);
        employee1.setHireDate(new Timestamp(20171020));
        employee1.setJobTitle("Petranstva");
        employee1.setMiddleName("P.");
        employee1.setDepartment(training);
        em.persist(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Ivan");
        employee2.setLastName("Ivanov");
        employee2.setAddress(address1);
        employee2.setHireDate(new Timestamp(20171020));
        employee2.setJobTitle("Just Javing");
        employee2.setMiddleName("J.");
        employee2.setDepartment(training);
        em.persist(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Svetlin");
        employee3.setLastName("Nakov");
        employee3.setAddress(address1);
        employee3.setHireDate(new Timestamp(20171020));
        employee3.setJobTitle("Inspiration Manager");
        employee3.setMiddleName("N.");
        employee3.setDepartment(training);
        em.persist(employee3);

        Employee employee4 = new Employee();
        employee4.setFirstName("Ebal");
        employee4.setLastName("Sym");
        employee4.setAddress(address2);
        employee4.setHireDate(new Timestamp(20171020));
        employee4.setJobTitle("Manager");
        employee4.setMiddleName("Go");
        employee4.setDepartment(training);
        em.persist(employee4);


        em.getTransaction().commit();

        em.close();
        entityManagerFactory.close();
    }
}
