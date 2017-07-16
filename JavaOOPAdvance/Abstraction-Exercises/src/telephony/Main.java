package telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Smartphone smartphone = new Smartphone();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] phones = reader.readLine().split("\\s+");

        for (String phone : phones) {
            System.out.println(smartphone.call(phone));
        }

        String[] emails = reader.readLine().split("\\s+");

        for (String email : emails) {
            System.out.println(smartphone.browseWeb(email));
        }
    }
}
