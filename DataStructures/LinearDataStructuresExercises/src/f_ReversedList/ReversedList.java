package f_ReversedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;


public class ReversedList<T> implements Iterable<T> {
    private final int INITIAL_CAPACITY = 2;

    private T[] items;

    private int count;

    public ReversedList() {
        this.items = (T[]) new Object[INITIAL_CAPACITY];
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public T get(int index) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException();
        }
        return items[items.length - index - 1];
    }

    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (this.count == this.items.length) {
            this.resize();
        }
        this.items[this.count++] = element;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        }

        //T[] tempItems = (T[]) Arrays.stream(items).filter(n -> !n.equals(null)).toArray();

        int indexToRemove = count - index - 1;

        this.items[indexToRemove] = null;
        this.shift(indexToRemove);
        this.count--;

        if (this.count <= this.items.length / 4) {
            this.shrink();
        }
    }



    private void shrink() {
        T[] copyArr = (T[]) new Object[this.items.length / 2];
        for (int i = 0; i < this.count; i++) {
            copyArr[i] = this.items[i];
        }
        this.items = copyArr;
    }

    private void shift(int index) {
        for (int i = index; i < this.count-1; i++) {
            this.items[i] = this.items[i+1];
            this.items[i + 1] = null;
        }
    }

    private void resize() {
        T[] copyArr = (T[]) new Object[this.count * 2];
        for (int i = 0; i < items.length; i++) {
            copyArr[i] = this.items[i];
        }

        this.items = copyArr;
    }

    @Override
    public String toString() {
        return Arrays.stream(this.items).filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new)).toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ReversedIterator();
    }

    private final class ReversedIterator implements Iterator<T> {

        private int counter = items.length - 1;

        @Override
        public boolean hasNext() {
            return counter >= 0;
        }

        @Override
        public T next() {
            while (Objects.isNull(items[counter])) {
                counter--;
            }
            counter--;
            return items[counter + 1];
        }
    }
}
