package loggers;

public class ErrorLogger extends Logger {
    @Override
    public void handle(LogType logType, String message) {
        if (logType == LogType.ERROR) {
            System.out.println(logType.name() + ": " + message);
        }

        super.passToSuccessor(logType, message);
    }
}
