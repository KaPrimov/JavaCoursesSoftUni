package P01.Logger.io;

import P01.Logger.interfaces.Logger;

import java.util.List;

public class OutpputWriter {

    public void writeData(List<Logger> loggers) {
        System.out.println("Logger info");
        for (Logger logger : loggers) {
            System.out.println(logger.toString());
        }
    }

}
