package P01.Logger.interfaces;

import P01.Logger.enums.ReportLevel;

public interface Layout {

    String prepareLayout(String date, ReportLevel severity, String message);
    String prepareLayout(String date, ReportLevel severity, String message, File file);

}
