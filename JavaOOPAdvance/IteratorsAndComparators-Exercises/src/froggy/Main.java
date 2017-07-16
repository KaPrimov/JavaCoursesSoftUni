package froggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> nums = Arrays.stream(reader.readLine().split("[\\s,]+")).map(Integer::parseInt).collect(Collectors.toList());
        reader.readLine();
        Lake lake = new Lake(nums);
        StringBuilder sb = new StringBuilder();
        for (Integer integer : lake) {
            sb.append(String.format("%s, ", integer));
        }

        sb.setLength(sb.length()-2);
        System.out.println(sb);
    }
}
