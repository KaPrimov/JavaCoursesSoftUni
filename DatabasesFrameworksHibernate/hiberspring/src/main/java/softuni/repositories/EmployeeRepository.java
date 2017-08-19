package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeCardNumber(String employeeCardNumber);

    @Query("SELECT e FROM Employee AS e " +
     //       "INNER JOIN e.branch " +
            "WHERE e.branch.id in " +
            "(SELECT p.branch.id FROM Product AS p) " +
            "ORDER BY concat(e.firstName, ' ', e.lastName), length(e.position) DESC")
    List<Employee> findAllProductiveEmployees();
}
