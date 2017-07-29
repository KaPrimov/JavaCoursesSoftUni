package softuni.course.automappinglab.service.api;

import softuni.course.automappinglab.entities.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeService {
    public  void registerEmployee(Employee employee);

    List<Employee> findAllEmployees(Date date);
}
