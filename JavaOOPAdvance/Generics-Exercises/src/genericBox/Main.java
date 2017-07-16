package genericBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List list = new List();
        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            Box box = new Box(Integer.parseInt(reader.readLine()));
            list.addBox(box);
        }

        int[] indexes = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        list.swap(indexes[0], indexes[1]);
        list.print();
    }
}
