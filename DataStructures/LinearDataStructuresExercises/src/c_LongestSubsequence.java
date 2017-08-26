import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class c_LongestSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] inputNumbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int bestLen = 1;
        int element = 0;

        for (int i = 0; i < inputNumbers.length; i++) {
            int currentLen = 1;
            for (int j = i + 1; j < inputNumbers.length; j++) {
                if (inputNumbers[i] == inputNumbers[j]) {
                    currentLen++;
                    if (currentLen > bestLen) {
                        bestLen = currentLen;
                        element = inputNumbers[i];
                    }
                } else {
                    break;
                }
            }
        }

        System.out.println(new String(new char[bestLen]).replace("\0", element + " "));

    }
}
