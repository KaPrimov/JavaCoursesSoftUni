package loggers;

public abstract class Logger implements Handler {

    private Handler successor;

    @Override
    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }

    protected void passToSuccessor(LogType logType, String message) {
        if (this.successor != null) {
            this.successor.handle(logType, message);
        }
    }
}
