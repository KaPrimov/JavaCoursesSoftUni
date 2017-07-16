package iteratorTest;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListIterator<T extends String> implements Iterable<T> {

    private List<T> strings;
    private int index;

    public ListIterator(T... elements) throws OperationNotSupportedException {
        setStrings(elements);
    }

    public int getIndex() {
        return index;
    }

    public List<T> getStrings() {
        return Collections.unmodifiableList(this.strings);
    }

    public boolean move() {
        if(this.iterator().hasNext()) {
            this.iterator().next();
            return true;
        }

        return false;
    }

    public boolean hasNext() {
        return this.iterator().hasNext();
    }

    public void print() {
        if(index >= this.strings.size()) {
            throw new IndexOutOfBoundsException("Invalid Operation !");
        }
        System.out.println(this.strings.get(index));

    }

    public void setStrings(T[] strings) throws OperationNotSupportedException {
        if(strings.length == 0) {
            throw new OperationNotSupportedException();
        }
        this.strings = new ArrayList<T>();
        Collections.addAll(this.strings, strings);
    }

    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    private final class InnerIterator implements Iterator<T> {


        public boolean hasNext() {
            if(index < strings.size()) {
                return true;
            }
            return false;
        }

        public T next() {
            if(hasNext()) {
                return strings.get(++index-1);
            }
            return null;
        }

        public void remove() {
            strings.remove(index);
        }
    }
}
