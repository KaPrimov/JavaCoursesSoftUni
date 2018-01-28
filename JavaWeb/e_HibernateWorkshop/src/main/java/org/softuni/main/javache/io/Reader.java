package org.softuni.main.javache.io;

import java.io.*;

public final class Reader {
    private Reader() {}

    public static String readAllLines(InputStream inputStream) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder result = new StringBuilder();

        while(buffer.ready()) {
            result.append((char)buffer.read());
        }

        return result.toString();
    }
}
