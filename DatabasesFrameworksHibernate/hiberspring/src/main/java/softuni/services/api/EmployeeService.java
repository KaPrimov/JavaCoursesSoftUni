package softuni.services.api;

import softuni.dto.binding.xmlWrappers.AddEmployeesXmlWrapper;
import softuni.dto.view.json.ProductiveEmployee;

import java.util.List;

public interface EmployeeService {
    void saveEmployees(AddEmployeesXmlWrapper employeesXmlWrapper);

    List<ProductiveEmployee> findAllProductiveEmployees();
}
