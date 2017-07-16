package customList;

import java.util.Collections;
import java.util.List;

public class Sorter<T extends Comparable<T>> implements Sortable<T> {


    @Override
    public void sort(List<T> list) {
        Collections.sort(list);
    }
}
