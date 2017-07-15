package stackIterator;

import java.util.Iterator;
import java.util.LinkedList;

public class StackIterator<T> implements Iterable<T> {

    private LinkedList<T> elements;

    public StackIterator() {
        this.elements = new LinkedList<>();
    }

    public void push(T element) {
        this.elements.add(element);
    }

    public T pop() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException("No elements");
        }
        return this.elements.remove(elements.size()-1);
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIteratorImpl();
    }

    private final class StackIteratorImpl implements Iterator<T> {

        private int index;

        public StackIteratorImpl() {
            this.index = elements.size()-1;
        }

        @Override
        public boolean hasNext() {
            return this.index >= 0;
        }

        @Override
        public T next() {
            return elements.get(this.index--);
        }
    }
}
