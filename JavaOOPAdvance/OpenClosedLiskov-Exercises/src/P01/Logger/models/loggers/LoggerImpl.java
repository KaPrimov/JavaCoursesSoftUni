package P01.Logger.models.loggers;

import P01.Logger.enums.ReportLevel;
import P01.Logger.interfaces.Appender;
import P01.Logger.interfaces.Logger;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class LoggerImpl implements Logger {

    private List<Appender> appenders;

    protected LoggerImpl(Appender... appenders) {
        this.setAppenders(appenders);
    }

    private void setAppenders(Appender[] appenders) {
        if (appenders.length == 0) {
            throw new IllegalArgumentException("You have to give an appender");
        }
        this.appenders = Arrays.asList(appenders);
    }

    @Override
    public void logMessage(String date, String message, String reportLevel) {
        for (Appender appender : appenders) {
            appender.saveFile(date, ReportLevel.valueOf(reportLevel.toUpperCase()), message);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Appender appender : appenders) {
            sb.append(appender.toString());
        }
        return sb.toString() ;
    }

    protected Iterable<Appender> getAppender() {
        return this.appenders;
    }
}
