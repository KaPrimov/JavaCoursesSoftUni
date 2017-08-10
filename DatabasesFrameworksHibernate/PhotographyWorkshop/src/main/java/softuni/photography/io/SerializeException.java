package softuni.photography.io;

public class SerializeException extends RuntimeException {
    public  SerializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public  SerializeException(String message) {
        super(message);
    }
}
