package com.KaPrim;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UserLogs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, LinkedHashMap<String, Integer>> dataLogs = new TreeMap<>();

        while (true) {
            String[] input = scanner.nextLine().trim().split(" ");
            if(input[0].equals("end")) {
                break;
            }
            String ip = input[0].substring(3, input[0].length());
            String user = input[2].substring(5, input[2].length());

            if(!dataLogs.containsKey(user)) {
                dataLogs.put(user, new LinkedHashMap<>());
            }

            if (!dataLogs.get(user).containsKey(ip)) {
                dataLogs.get(user).put(ip, 1);
            } else {
                dataLogs.get(user).put(ip, dataLogs.get(user).get(ip) + 1);
            }
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, LinkedHashMap<String, Integer>> dataLog : dataLogs.entrySet()) {
            result.append(String.format("%s:%n", dataLog.getKey()));
            LinkedHashMap<String, Integer> value = dataLog.getValue();
            for (Map.Entry<String, Integer> entry : value.entrySet()) {
                result.append(String.format("%s => %d, ", entry.getKey(), entry.getValue()));
            }
            result.delete(result.length()-2, result.length());
            result.append(".\n");
        }

        System.out.println(result);
    }
}
