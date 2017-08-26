import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class d_RemoveOddOccurences {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> numbersInput = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::valueOf).collect(Collectors.toList());

        List<Integer> rightNums = new ArrayList<>();
        for (int i = 0; i < numbersInput.size(); i++) {
            int count = 1;
            int currentNum = numbersInput.get(i);
            if (rightNums.contains(currentNum)) {
                continue;
            }
            for (int j = i + 1; j < numbersInput.size(); j++) {
                if (Objects.equals(numbersInput.get(i), numbersInput.get(j))) {
                    count++;
                }
            }
            if (count % 2 == 1) {
                numbersInput.removeIf(n -> n.equals(currentNum));
                i-= count;
                if (i < -1) {
                    i = -1;
                }
            } else {
                rightNums.add(currentNum);
            }
        }

        System.out.println(numbersInput.toString().replaceAll("[\\[\\],]", ""));
    }
}
