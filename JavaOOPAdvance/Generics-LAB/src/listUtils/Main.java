package listUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, 1 , 2, 4, -12, 100);
        System.out.println(ListUtils.getMax(integers));
        System.out.println(ListUtils.getMin(integers));
    }
}
