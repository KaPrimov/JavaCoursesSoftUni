package genericCountMethodString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.valueOf(reader.readLine());
        List<Double> elements = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            elements.add(Double.parseDouble(reader.readLine()));
        }
        Double toCompare = Double.parseDouble(reader.readLine());
        int result = getCount(elements, toCompare);
        System.out.println(result);
    }

    private static <T extends Comparable<T>> int getCount(List<T> elements, T toCompare) {
        int count = 0;

        for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).compareTo(toCompare) > 0) {
                count++;
            }
        }

        return count;
    }
}
