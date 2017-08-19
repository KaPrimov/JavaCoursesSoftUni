package softuni.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.dto.binding.AddEmployeeXmlDto;
import softuni.dto.binding.xmlWrappers.AddEmployeesXmlWrapper;
import softuni.dto.view.json.ProductiveEmployee;
import softuni.entities.Branch;
import softuni.entities.Employee;
import softuni.entities.EmployeeCard;
import softuni.io.MessageWriter;
import softuni.repositories.BranchRepository;
import softuni.repositories.EmployeeCardRepository;
import softuni.repositories.EmployeeRepository;
import softuni.services.api.EmployeeService;
import softuni.utils.CustomValidator;
import softuni.utils.DTOConvertUtil;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository employeeCardRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeCardRepository, BranchRepository branchRepository, EmployeeCardRepository employeeCardRepository1) {
        this.employeeRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository1;
    }

    @Override
    public void saveEmployees(AddEmployeesXmlWrapper employeesXmlWrapper) {
        for (AddEmployeeXmlDto addEmployeeXmlDto : employeesXmlWrapper.getEmployees()) {
            boolean isValid = true;
            Branch branch = this.branchRepository.findByName(addEmployeeXmlDto.getBranch());
            EmployeeCard employeeCard = this.employeeCardRepository.findByNumber(addEmployeeXmlDto.getCard());
            if (branch == null || employeeCard == null) {
                isValid = false;
            }

            Employee employee = DTOConvertUtil.convert(addEmployeeXmlDto, Employee.class);
            employee.setBranch(branch);
            employee.setEmployeeCard(employeeCard);
            if (!CustomValidator.isValid(employee)) {
                isValid = false;
            }

            Employee duplicatedEmployeeCardCheck = this.employeeRepository.findByEmployeeCardNumber(addEmployeeXmlDto.getCard());

            if (duplicatedEmployeeCardCheck != null) {
                isValid = false;
            }

            if (isValid) {
                this.employeeRepository.save(employee);
                MessageWriter.getInstance().printSuccess(Employee.class, employee.getFirstName() + " " + employee.getLastName());
            } else {
                MessageWriter.getInstance().printError();
            }
        }
    }

    @Override
    public List<ProductiveEmployee> findAllProductiveEmployees() {
        List<Employee> productiveEmployees = this.employeeRepository.findAllProductiveEmployees();

        return DTOConvertUtil.convert(productiveEmployees, ProductiveEmployee.class);
    }
}
