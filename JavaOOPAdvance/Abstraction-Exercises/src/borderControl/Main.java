package borderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Buyer> buyers = new HashMap<>();
        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            Buyer buyer = null;
            if(tokens.length == 3 ) {
                buyer = new Rebel(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            } else if (tokens.length == 4) {
                buyer = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
            }

            buyers.put(tokens[0], buyer);
        }

        while (true) {
            String name = reader.readLine();
            if("End".equals(name)) {
                break;
            }

            if(buyers.containsKey(name)) {
                buyers.get(name).buyFood();

            }
        }

        System.out.println(buyers.values().stream().mapToInt(Buyer::getFood).sum());

    }
}
