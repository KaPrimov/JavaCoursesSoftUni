package bubbleSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bubble<T extends Comparable<T>> {

    private List<T> elements;

    public Bubble(T... elements) {
        setElements(elements);
    }

    public void setElements(T[] elements) {
        if(elements.length == 0) {
            throw new IllegalArgumentException("The elements have to be more than 0");
        }

        this.elements = new ArrayList<T>();
        Collections.addAll(this.elements, elements);
    }

    private void sortElements() {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < this.elements.size(); i++) {
                T temp = null;
                if(this.elements.get(i - 1).compareTo(this.elements.get(i)) > 0) {
                    temp = this.elements.get(i-1);
                    this.elements.set(i-1, this.elements.get(i));
                    this.elements.set(i, temp);
                    swapped = true;
                }
            }
        }
    }

    public List<T> returnElements() {
        sortElements();
        return Collections.unmodifiableList(this.elements);
    }
}
