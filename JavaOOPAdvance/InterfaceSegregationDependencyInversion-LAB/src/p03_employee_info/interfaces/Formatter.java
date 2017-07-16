package p03_employee_info.interfaces;

import p03_employee_info.Employee;

public interface Formatter {
    String format(Iterable<Employee> employees);
}
