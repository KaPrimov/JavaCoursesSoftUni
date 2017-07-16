package P01.Logger.models.loggers;

import P01.Logger.interfaces.Appender;

public class FileLogger extends LoggerImpl {

    public FileLogger(Appender... appenders) {
        super(appenders);
    }

}
