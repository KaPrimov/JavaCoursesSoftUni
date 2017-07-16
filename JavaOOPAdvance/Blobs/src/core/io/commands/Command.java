package core.io.commands;

import com.sun.javaws.exceptions.InvalidArgumentException;
import interfaces.Executable;

public abstract class Command implements Executable {
    private String[] data;

    protected Command(String[] data) {
        this.setData(data);
    }


    protected String[] getData() {
        return data;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            try {
                throw new InvalidArgumentException(data);
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
        }
        this.data = data;
    }
}
