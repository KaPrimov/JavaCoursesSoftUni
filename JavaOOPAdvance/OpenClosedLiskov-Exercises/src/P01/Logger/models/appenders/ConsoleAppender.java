package P01.Logger.models.appenders;

import P01.Logger.enums.ReportLevel;
import P01.Logger.interfaces.Appender;
import P01.Logger.interfaces.Layout;

public class ConsoleAppender extends AppenderImpl {

    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    @Override
    public void saveFile(String date, ReportLevel severity, String message) {
        if (severity.ordinal() >= super.getReportLevel().ordinal()) {
            super.saveMessage();
            System.out.println(super.getLayout().prepareLayout(date, severity, message));
        }
    }
}
