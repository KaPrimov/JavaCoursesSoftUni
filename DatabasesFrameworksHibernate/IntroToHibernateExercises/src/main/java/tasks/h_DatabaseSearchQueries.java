package tasks;

import entities.Address;
import entities.Department;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class h_DatabaseSearchQueries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        //first task
        Query addresQuery = em.createQuery("SELECT a from Address as a " +
                "inner join Employee as e " +
                "on e.address.id = a.id " +
                "inner join Town as t " +
                "on t.id = a.town.id");

        List<Address> addresses = addresQuery.getResultList();

        addresses.stream()
                .distinct()
                .sorted((a,b) -> {
                    int index = b.getEmployees().size() - a.getEmployees().size();

                    if (index == 0) {
                        index = a.getTown().getName().compareTo(b.getTown().getName());
                    }
                    return index;
                })
                .limit(10)
                .forEach(a -> {
                    System.out.printf("%s, %s, %d employees%n", a.getText(), a.getTown().getName(), a.getEmployees().size());
                });

        //second task
        System.out.println(new String(new char[50]).replace("\0", "-"));

        Employee singleEmployee = (Employee) em.createQuery("SELECT e FROM Employee AS e WHERE e.id = 147").getSingleResult();

        Query findEmployeeProjects = em.createQuery("SELECT p FROM Project AS p " +
                "WHERE p.id IN :projects").setParameter("projects",
                singleEmployee.getProjects().stream()
                        .map(Project::getId)
                        .collect(Collectors.toCollection(ArrayList::new)));

        List<Project> projects = findEmployeeProjects.getResultList();

        System.out.printf("%s %s %s%n", singleEmployee.getFirstName(),
                singleEmployee.getLastName(),
                singleEmployee.getJobTitle());
        for (Project project : projects) {
            System.out.println(project.getName());
        }

        //third task
        System.out.println(new String(new char[50]).replace("\0", "-"));

        List<Integer> projectIdsInTheTimeSpan = em.createQuery(
                "SELECT p.id FROM Project AS p " +
                    "WHERE DATE_FORMAT(p.startDate, '%Y') BETWEEN 2001 AND 2003"
        ).getResultList();

        Query employeesQuery = em.createQuery("SELECT e FROM Employee AS e " +
                "INNER JOIN e.projects AS p " +
                "WHERE p.id IN (:ids)");
        employeesQuery.setParameter("ids", projectIdsInTheTimeSpan);

        List<Employee> employees = employeesQuery.getResultList();

        for (Employee employee : employees) {
            System.out.println(String.format("Name: %s %s; Manager: %s", employee.getFirstName(), employee.getLastName(), employee.getManager().getFirstName()));
            for (Project project : employee.getProjects()) {
               // if (projectIdsInTheTimeSpan.contains(project.getId())) {

                    System.out.printf("###Project name: %s, From %s To %s%n",
                            project.getName(), project.getStartDate(), project.getEndDate());
               // }
            }
        }

        //fourth task
        System.out.println(new String(new char[50]).replace("\0", "-"));

        Query departmentsQuery = em.createQuery("SELECT d FROM Department AS d " +
                "WHERE d.employees.size > 5");

        List<Department> departments = departmentsQuery.getResultList();

        System.out.println(departments.size());

        departments.stream()
                .sorted(Comparator.comparingInt(a -> a.getEmployees().size()))
                .forEach(d -> {
                    System.out.printf("*****%s - Manager: %s, Employees: %d%n*****",
                            d.getName(), d.getManager().getLastName(), d.getEmployees().size());

                    for (Employee employee : d.getEmployees()) {
                        System.out.printf("-----%s %s, Hire Date: %s, Position: %s%n",
                                employee.getFirstName(),
                                employee.getLastName(),
                                new SimpleDateFormat("dd/MMM/yyyy").format(employee.getHireDate()),
                                employee.getJobTitle());
                    }
                });

        em.close();
        emf.close();
    }
}
