package P01.Logger.interfaces;

import P01.Logger.enums.ReportLevel;

public interface Appender {

    void saveFile(String date, ReportLevel severity, String message);
    void setReportLevel(ReportLevel reportLevel);

}
