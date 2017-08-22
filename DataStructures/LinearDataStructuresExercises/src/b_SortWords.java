import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class b_SortWords {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> words = Arrays.asList(reader.readLine().split(" "));

        Collections.sort(words);

        System.out.println(words.toString().replaceAll("[\\[\\],]", ""));
    }
}
