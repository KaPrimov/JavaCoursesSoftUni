import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class e_OddOccurences {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> inputNumbers = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::valueOf).collect(Collectors.toList());

        Map<Integer, Integer> occurences = new TreeMap<>();

        for (int i = 0; i < inputNumbers.size(); i++) {
            int count = 1;
            int currentNum = inputNumbers.get(i);
            for (int j = i + 1; j < inputNumbers.size(); j++) {
                if (Objects.equals(inputNumbers.get(i), inputNumbers.get(j))) {
                    count++;
                }
            }
            occurences.put(inputNumbers.get(i), count);

            inputNumbers.removeIf(n -> n.equals(currentNum));
            i = i-count < -1 ? -1 : i-count;
        }

        for (Map.Entry<Integer, Integer> es : occurences.entrySet()) {
            System.out.println(es.getKey() + " -> " + es.getValue() + " times");
        }
    }
}
