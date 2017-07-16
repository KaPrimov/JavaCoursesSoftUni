package warningLevels;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private Importance level;
    private List<Message> messages;

    public Logger(Importance level) {
        this.level = level;
        this.messages = new ArrayList<>();
    }

    public void log(Message message) {
        if (message.getLevel().compareTo(this.level) >= 0) {
            this.messages.add(message);
        }
    }

    public Iterable<Message> getMessages() {
       return this.messages;
    }
}
