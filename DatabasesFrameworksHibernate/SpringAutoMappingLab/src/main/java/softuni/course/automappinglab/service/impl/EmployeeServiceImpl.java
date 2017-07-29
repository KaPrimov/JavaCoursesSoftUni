package softuni.course.automappinglab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.course.automappinglab.dao.EmployeesDao;
import softuni.course.automappinglab.entities.Employee;
import softuni.course.automappinglab.service.api.EmployeeService;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeesDao employeesDao;

    @Autowired
    public EmployeeServiceImpl(EmployeesDao employeesDao) {
        this.employeesDao = employeesDao;
    }


    @Override
    public void registerEmployee(Employee employee) {
        this.employeesDao.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees(Date date) {
        return this.employeesDao.findByBirthDateBeforeOrderBySalaryDesc(date);
    }
}
