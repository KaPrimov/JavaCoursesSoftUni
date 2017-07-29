package softuni.course.automappinglab.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import softuni.course.automappinglab.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class DtoMappingUtil {

    private DtoMappingUtil() {
    }

    public static EmployeeDto convertEmployee(Employee employee) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(employee, EmployeeDto.class);
    }

    public static <S, D> D convert(S source, Class<D> destClass) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(source, destClass);
    }

    public static <S, D> List<D> convert(Iterable<S> sources, Class<D> destClass) {
        ModelMapper modelMapper = new ModelMapper();
        List<D> resultList = new ArrayList<>();
        for (S source : sources) {
            D dest = convert(source, destClass);
            resultList.add(dest);
        }
        return resultList;
    }
    public static EmployeeDto convert(Employee source) {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<Employee, EmployeeDto> propertyMap = new PropertyMap<Employee, EmployeeDto>() {
            @Override
            protected void configure() {
                map().setManagerLastName(source.getManager().getLastName());
            }
        };

        modelMapper.addMappings(propertyMap);
        return modelMapper.map(source, EmployeeDto.class);
    }

    public static List<EmployeeDto> convert(List<Employee> sources) {
        List<EmployeeDto> result = new ArrayList<>();
        for (Employee employee : sources) {
            result.add(convert(employee));
        }
        return result;
    }

}
