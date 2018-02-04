package com.KaPrim;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MinersTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Long> preciousMetals = new LinkedHashMap<>();
        while (true) {
            String resource = scanner.nextLine();
            if(resource.equals("stop")) {
                break;
            }

            if (!preciousMetals.containsKey(resource)) {
                preciousMetals.put(resource, 0L);
            }
            long quantity = Long.parseLong(scanner.nextLine());
            preciousMetals.put(resource, preciousMetals.get(resource) + quantity );

        }

        for (String preciousMetal : preciousMetals.keySet()) {
            System.out.printf("%s -> %d%n", preciousMetal, preciousMetals.get(preciousMetal));
        }
    }
}
