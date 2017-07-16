package P01.Logger.interfaces;

import P01.Logger.enums.ReportLevel;

public interface File {

    public void write(String message, ReportLevel severity, String date, Layout layout);
    public long getSize();
}
