import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class a_SumAndAverage {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbersInput = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.printf("Sum=%d; Average=%.2f", Arrays.stream(numbersInput).sum()
                , Arrays.stream(numbersInput).average().isPresent()? Arrays.stream(numbersInput).average().getAsDouble() : 0.00);

    }
}
