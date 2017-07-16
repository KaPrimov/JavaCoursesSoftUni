package militaryElite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandInterpretator interpretator = new CommandInterpretator();

        while (true){
            String[] params = reader.readLine().split("\\s+");
            if (params[0].equals("End")){
                break;
            }
            try{
                interpretator.createSoldier(params);
            } catch (IllegalArgumentException ex){
            }
        }
    }
}
