package com.KaPrim;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FixEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String > emails = new LinkedHashMap<>();
        while (true) {
            String name = scanner.nextLine();
            if(name.equals("stop")) {
                break;
            }

            if(!emails.containsKey(name)) {
                emails.put(name, "");
            }

            String email = scanner.nextLine();
            String domainEnd = email.substring(email.indexOf(".")+1, email.length());

            if(domainEnd.toLowerCase().equals("us") || domainEnd.toLowerCase().equals("uk")
                    || domainEnd.toLowerCase().equals("com")) {
                continue;
            } else {
                emails.put(name, email);
            }
        }

        for (String name : emails.keySet()) {
            if(!emails.get(name).equals("")) {
                System.out.printf("%s -> %s%n", name, emails.get(name));
            }
        }
    }
}
