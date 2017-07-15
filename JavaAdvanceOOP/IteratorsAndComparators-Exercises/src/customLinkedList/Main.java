package customLinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CustomLinkedList<Integer> numbers = new CustomLinkedList<>();
        int n = Integer.valueOf(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] params = reader.readLine().split("\\s+");
            String command = params[0];
            int num = Integer.valueOf(params[1]);

            switch (command){
                case "Add":
                    numbers.add(num);
                    break;

                case "Remove":
                    numbers.remove(num);
                    break;
            }
        }
        System.out.println(numbers.getCount());
        numbers.forEach(e -> System.out.printf("%d ", e));
    }
}