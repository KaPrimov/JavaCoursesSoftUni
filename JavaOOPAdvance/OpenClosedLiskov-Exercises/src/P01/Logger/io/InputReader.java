package P01.Logger.io;

import P01.Logger.enums.ReportLevel;
import P01.Logger.factories.AppenderFactory;
import P01.Logger.factories.LayoutFactory;
import P01.Logger.interfaces.Appender;
import P01.Logger.interfaces.Layout;
import P01.Logger.interfaces.Logger;
import P01.Logger.models.loggers.MessageLogger;

import java.util.List;

public class InputReader {

    private LayoutFactory layoutFactory;
    private AppenderFactory appenderFactory;

    public InputReader(LayoutFactory layoutFactory, AppenderFactory appenderFactory) {
        this.layoutFactory = layoutFactory;
        this.appenderFactory = appenderFactory;
    }

    public Logger readLoggerDetails(String input) {
        String[] tokens = input.split("\\s+");
        Layout layout = this.layoutFactory.returnObject(tokens[1]);
        Appender appender = this.appenderFactory.returnObject(layout, tokens[0]);
        if(tokens.length == 3) {
            appender.setReportLevel(ReportLevel.valueOf(tokens[2]));
        }
        return new MessageLogger(appender);
    }

    public void addMessage(String input, List<Logger> loggers) {
        String[] tokens = input.split("\\|");
        String severity = tokens[0];
        String date = tokens[1];
        String message = tokens[2];

        for (Logger logger : loggers) {
            logger.logMessage(date, message, severity);
        }
    }

}
