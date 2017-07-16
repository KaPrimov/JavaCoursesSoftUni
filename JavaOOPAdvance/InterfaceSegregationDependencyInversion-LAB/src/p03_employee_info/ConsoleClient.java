package p03_employee_info;

import p03_employee_info.interfaces.Client;
import p03_employee_info.interfaces.Formatter;
import p03_employee_info.interfaces.InfoProvider;

public class ConsoleClient implements Client {

    private Formatter formatter;
    private InfoProvider infoProvider;

    public ConsoleClient(Formatter formatter, InfoProvider infoProvider) {
        this.formatter = formatter;
        this.infoProvider = infoProvider;
    }

    @Override
    public void printInfo() {
        String output = formatter.format(infoProvider.getEmployeesByName());
        System.out.println(output);
    }
}
