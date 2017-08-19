package softuni.dto.binding.xmlWrappers;

import softuni.dto.binding.AddEmployeeXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employees")
public class AddEmployeesXmlWrapper {

    @XmlElement(name = "employee")
    private List<AddEmployeeXmlDto> employees;

    public AddEmployeesXmlWrapper() {
    }

    public List<AddEmployeeXmlDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<AddEmployeeXmlDto> employees) {
        this.employees = employees;
    }
}
