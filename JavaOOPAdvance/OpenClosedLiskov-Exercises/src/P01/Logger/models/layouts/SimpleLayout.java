package P01.Logger.models.layouts;

import P01.Logger.enums.ReportLevel;
import P01.Logger.interfaces.File;
import P01.Logger.interfaces.Layout;

public class SimpleLayout implements Layout {

    @Override
    public String prepareLayout(String date, ReportLevel severity, String message) {
        return String.format("%s - %s - %s", date, severity.name(), message);
    }

    @Override
    public String prepareLayout(String date, ReportLevel severity, String message, File file) {
        return String.format("%s - %s - %s - File size: %d", date, severity.name(), message, file.getSize());
    }

}
