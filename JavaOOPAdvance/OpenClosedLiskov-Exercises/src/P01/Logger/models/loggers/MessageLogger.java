package P01.Logger.models.loggers;

import P01.Logger.interfaces.Appender;


public class MessageLogger extends LoggerImpl {

    public MessageLogger(Appender... appenders) {
        super(appenders);
    }



}
