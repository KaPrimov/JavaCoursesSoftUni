import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class b_CalculateSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(reader.readLine().trim());
        Deque<Long> queue = new ArrayDeque<>();
        StringBuilder result = new StringBuilder("" + n);
        queue.add(n);
        for (int i = 1; i < 50; i++) {
            long temp;
            if (i % 3 == 0) {
                temp = queue.poll() + 2;
                queue.add(temp);
            } else if (i % 3 == 1) {
                temp = queue.peek() + 1;
                queue.add(temp);
            } else {
                temp = 2 * queue.peek() + 1;
                queue.add(temp);
            }
            result.append(", ").append(temp);
        }
        System.out.println(result);
    }
}
