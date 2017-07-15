package MordorsParty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> foods = new HashMap<>();
        int gandalfMoood = 0;
        initializeFoods(foods);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] foodInput = reader.readLine().toLowerCase().split("\\s+");
        for (String food : foodInput) {
            if (foods.containsKey(food)){
                gandalfMoood += foods.get(food);
            } else {
                gandalfMoood--;
            }
        }
        System.out.println(gandalfMoood);
        if (gandalfMoood < -5) {
            System.out.println("Angry");
        } else if (gandalfMoood >= -5 && gandalfMoood < 0 ) {
            System.out.println("Sad");
        } else if (gandalfMoood >= 0 && gandalfMoood < 15) {
            System.out.println("Happy");
        } else {
            System.out.println("JavaScript");
        }
    }

    private static void initializeFoods(HashMap<String, Integer> foods) {
        foods.put("cram", 2);
        foods.put("lembas", 3);
        foods.put("apple", 1);
        foods.put("melon", 1);
        foods.put("honeycake", 5);
        foods.put("mushrooms", -10);
    }
}
