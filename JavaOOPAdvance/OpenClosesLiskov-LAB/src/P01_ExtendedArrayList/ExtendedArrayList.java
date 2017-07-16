package P01_ExtendedArrayList;

import java.util.ArrayList;
import java.util.Iterator;

public class ExtendedArrayList<T extends Comparable<T>> extends ArrayList<T>{

    public T max() {
        Iterator<T> iterator = super.iterator();
        T max = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (max == null ||  iterator.next().compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public T min() {
        Iterator<T> iterator = super.iterator();
        T min = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (min == null || iterator.next().compareTo(min) <= 0) {
                min = element;
            }
        }
        return min;
    }
}
