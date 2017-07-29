package softuni.course.automappinglab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.course.automappinglab.entities.Employee;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeesDao extends JpaRepository<Employee, Long> {

    List<Employee> findByBirthDateBeforeOrderBySalaryDesc(Date birthDate);


}
