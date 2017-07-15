package pawinc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        AnimalCenterManager manager = new AnimalCenterManager();
        String line = reader.readLine();
        while (!line.equals("Paw Paw Pawah")) {

            String[] tokens = line.split(" \\| ");

            String command = tokens[0];

            switch (command) {
                case "RegisterCleansingCenter":
                    manager.registerCleansingCenter(tokens[1]);
                    break;
                case "RegisterAdoptionCenter":
                    manager.registerAdoptionCenter(tokens[1]);
                    break;
                case "RegisterDog":
                    int age = Integer.parseInt(tokens[2]);
                    int commands = Integer.parseInt(tokens[3]);
                    String adiptionCenter = tokens[4];
                    manager.registerDog(tokens[1], age, commands, adiptionCenter);
                    break;
                case "RegisterCat":
                    int ageCat = Integer.parseInt(tokens[2]);
                    int intelligence = Integer.parseInt(tokens[3]);
                    String adiptionCenteCat = tokens[4];
                    manager.registerCat(tokens[1], ageCat, intelligence, adiptionCenteCat);
                    break;
                case "SendForCleansing":
                    manager.sendForCleansing(tokens[1], tokens[2]);
                    break;
                case "Cleanse":
                    manager.cleanse(tokens[1]);
                    break;
                case "Adopt":
                    manager.adopt(tokens[1]);
                    break;
                case "RegisterCastrationCenter":
                    manager.registerCastrationCenter(tokens[1]);
                    break;
                case "SendForCastration":
                    manager.sendForCastration(tokens[1], tokens[2]);
                    break;
                case "Castrate":
                    manager.castrate(tokens[1]);
                    break;
                case "CastrationStatistics":
                    manager.printCastrationStatistics();
                    break;
                default:
                    break;
            }
            line =reader.readLine();
        }
        manager.printStatistics();
    }
}
