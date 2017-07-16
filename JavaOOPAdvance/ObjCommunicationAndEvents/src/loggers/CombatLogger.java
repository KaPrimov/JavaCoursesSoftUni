package loggers;

public class CombatLogger extends Logger {


    @Override
    public void handle(LogType logType, String message) {
        if (logType == LogType.ATTACK || logType == LogType.MAGIC || logType == LogType.TARGET) {
            System.out.println(logType.name() + ": " + message);
        }
        super.passToSuccessor(logType, message);
    }
}
