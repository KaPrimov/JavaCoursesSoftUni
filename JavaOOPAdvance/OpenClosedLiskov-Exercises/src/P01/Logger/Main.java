package P01.Logger;

import P01.Logger.factories.AppenderFactory;
import P01.Logger.factories.LayoutFactory;
import P01.Logger.interfaces.Logger;
import P01.Logger.io.InputReader;
import P01.Logger.io.OutpputWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Logger> loggers = new ArrayList<>();
        LayoutFactory layoutFactory = new LayoutFactory();
        AppenderFactory appenderFactory = new AppenderFactory();
        InputReader inputReader = new InputReader(layoutFactory, appenderFactory);
        for (int i = 0; i < n; i++) {
            loggers.add(inputReader.readLoggerDetails(reader.readLine()));
        }
        OutpputWriter outpputWriter = new OutpputWriter();
        String line = reader.readLine();
        while (true) {
            if(line.equals("END")) {
                outpputWriter.writeData(loggers);
                break;
            }

            inputReader.addMessage(line, loggers);
            line = reader.readLine();
        }
    }
}
