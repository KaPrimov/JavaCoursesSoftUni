package loggers;

public interface Handler {
    void handle(LogType logType, String message);
    void setSuccessor(Handler handler);
}
