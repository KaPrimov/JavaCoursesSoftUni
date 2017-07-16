package customList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class  customList<T extends Comparable<T>> implements Iterable<T> {

    private List<T> list;
    private Sortable<T> sorter;
    public customList() {
        this.list = new ArrayList<>();
        this.sorter = new Sorter<T>();
    }

    public void sort() {
        this.sorter.sort(list);
    }

    public void add(T element) {
        this.list.add(element);
    }

    public T remove(int index) {
        return this.list.remove(index);
    }

    public boolean contains(T element) {
        return this.list.contains(element);
    }

    public void swap(int indexFrom, int indexTo) {
        Collections.swap(this.list, indexFrom, indexTo);
    }

    public int countGreaterThan(T element) {
        int max = 0;

        for (int i = 0; i < list.size(); i++) {
            if(element.compareTo(list.get(i)) < 0) {
                max++;
            }
        }
        return max;
    }

    public T getMax() {
        T max = list.get(0);

        for (int i = 1; i < list.size() ; i++) {
            if(max.compareTo(list.get(i)) < 0) {
                max = list.get(i);
            }
        }
        return max;
    }

    public T getMin() {
        T min = list.get(0);

        for (int i = 1; i < list.size() ; i++) {
            if(min.compareTo(list.get(i)) > 0) {
                min = list.get(i);
            }
        }
        return min;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < list.size();
            }

            @Override
            public T next() {
                return list.get(index++);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (Iterator<T> iter = list.iterator(); iter.hasNext();) {
            T var = iter.next();
            action.accept(var);
        }
    }
}
