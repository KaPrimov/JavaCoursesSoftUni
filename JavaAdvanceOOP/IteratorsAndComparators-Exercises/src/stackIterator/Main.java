package stackIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StackIterator<Integer> stackIterator = new StackIterator<>();
        while (true) {
            String[] tokens = reader.readLine().split("[\\s,]+");
            if ("END".equals(tokens[0])) {
                break;
            }

            String command = tokens[0];
            try {
                switch (command) {
                    case "Push":
                        for (int i = 1; i < tokens.length; i++) {
                            stackIterator.push(Integer.parseInt(tokens[i]));
                        }
                        break;
                    case "Pop":
                        stackIterator.pop();
                        break;
                }
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
        }

        for (int i = 0; i < 2; i++) {
            for (Integer integer : stackIterator) {
                System.out.println(integer);
            }
        }
    }
}
