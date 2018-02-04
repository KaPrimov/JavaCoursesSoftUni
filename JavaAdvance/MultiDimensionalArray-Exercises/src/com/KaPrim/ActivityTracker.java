package com.KaPrim;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ActivityTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        TreeMap<Integer, MonthReport> allReports = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            String[] date = input[0].split("/");

            int month = Integer.parseInt(date[1]);
            String user = input[1];
            int distance = Integer.parseInt(input[2]);

            if (allReports.containsKey(month)) {
                MonthReport tempReport = allReports.get(month);
                tempReport.addData(user, distance);
            } else {
                MonthReport tempReport = new MonthReport(month);
                tempReport.addData(user, distance);
                allReports.put(month, tempReport);
            }
        }
        for(Map.Entry<Integer, MonthReport> singleReport : allReports.entrySet()) {
            MonthReport currentReport = singleReport.getValue();

            String  answer = currentReport.printData();
            System.out.println(answer.substring(0, answer.length()-2));
        }

    }
    private static class MonthReport {
        private int name;
        private TreeMap<String, Long> monthData;

        public MonthReport(int name) {
            this.name = name;
            this.monthData = new TreeMap<>();
        }

        public void addData(String name, int distance) {
            if (monthData.containsKey(name)) {
                long tempValue = monthData.get(name);
                monthData.put(name, tempValue + distance);
            } else {
                monthData.put(name, (long) distance);
            }
        }

        public String printData() {
            String answer = this.name + ": ";
            for(Map.Entry<String,Long> entry : monthData.entrySet()) {
                String key = entry.getKey();
                Long value = entry.getValue();
                answer+= (key + "(" + value + ")" + ", ");
            }
            return answer;
        }
    }
}
