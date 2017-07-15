package tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String[] firstTokens = reader.readLine().split("\\s+");
        String[] secondTokens = reader.readLine().split("\\s+");
        boolean state = secondTokens[2].equals("drunk");
        String[] thirdTokens = reader.readLine().split("\\s+");
        Tuple<String , String , String> firstTuple = new Tuple<>(firstTokens[0] + " " + firstTokens[1], firstTokens[2], firstTokens[3]);
        Tuple<String , Integer , Boolean> secondTuple = new Tuple<>(secondTokens[0], Integer.parseInt(secondTokens[1]), state);
        Tuple<String , Double , String> thirdTuple = new Tuple<>(thirdTokens[0], Double.parseDouble(thirdTokens[1]), thirdTokens[2]);
        System.out.println(firstTuple);
        System.out.println(secondTuple);
        System.out.println(thirdTuple);
    }
}
