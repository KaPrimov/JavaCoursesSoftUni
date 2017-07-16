package p03_employee_info;

import p03_employee_info.interfaces.Client;
import p03_employee_info.interfaces.Database;
import p03_employee_info.interfaces.Formatter;
import p03_employee_info.interfaces.InfoProvider;

public class Main {

    public static void main(String[] args) {
        Database database = new EmployeeDatabase();
        InfoProvider employeeInfo = new EmployeeInfoProvider(database);
        Formatter formatter = new ConsoleFormatter();

        Client client = new ConsoleClient(formatter, employeeInfo);
        client.printInfo();
    }
}
