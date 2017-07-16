package listIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListIterator<T> implements Iterable<T> {

    private List<T> elements;
    private int index;

    public ListIterator(T... elements) {
        setElements(elements);
        index = 0;
    }

    private void setElements(T... elements) {
        this.elements = new ArrayList<>(Arrays.asList(elements));
    }

    public boolean hasNext() {
        if(this.index + 1 < this.elements.size()) {
            return true;
        }
        return false;
    }

    public boolean move() {
        if(this.hasNext()) {
            index++;
            return true;
        }
        return false;
    }

    public T print() {
        if(this.elements.isEmpty()) {
            throw new NullPointerException("Invalid Operation!");
        }
        return elements.get(this.index);
    }

    public String printAll() {
        StringBuilder stringBuilder = new StringBuilder();
        this.elements.forEach(e -> stringBuilder.append(String.format("%s ", e)));
        return stringBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIteratorImpl();
    }

    private final class ListIteratorImpl implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return index < elements.size();
        }

        @Override
        public T next() {
            return elements.get(index++);
        }
    }
}
