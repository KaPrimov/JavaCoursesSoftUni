import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomList<T> {
    private final int INITIAL_CAPACITY = 2;

    private T[] items;

    private int count;

    public CustomList() {
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
        return items[index];
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

        this.items[index] = null;
        this.shift(index);
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
}
