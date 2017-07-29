package softuni.course.automappinglab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.course.automappinglab.dto.DtoMappingUtil;
import softuni.course.automappinglab.dto.EmployeeDto;
import softuni.course.automappinglab.dto.ManagerDto;
import softuni.course.automappinglab.entities.Address;
import softuni.course.automappinglab.entities.City;
import softuni.course.automappinglab.entities.Employee;
import softuni.course.automappinglab.service.api.EmployeeService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private final EmployeeService employeeService;

    @Autowired
    public Terminal(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... strings) throws Exception {

        City city = new City("Sofia", "Europe");
//
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Address address = new Address(city, "Tintyava");
        Employee employee = new Employee("Pesho", "Peshov", BigDecimal.valueOf(1293), sdf.parse("01-10-1994"), address);
        Employee manager = new Employee("PeshoM", "PeshovM", BigDecimal.valueOf(12223), sdf.parse("01-10-1994"), address);
        manager.addManagedEmployee(employee);
        employee.setManager(manager);
        employeeService.registerEmployee(manager);
        ManagerDto managerDto = DtoMappingUtil.convert(manager, ManagerDto.class);

        List<Employee> allEmployees = this.employeeService.findAllEmployees(sdf.parse("01-10-1992"));
        List<EmployeeDto> employeeDtos = DtoMappingUtil.convert(allEmployees);

        String debug = "";
    }
}
