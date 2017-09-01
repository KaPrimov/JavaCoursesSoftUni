import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;
    public BinaryHeap() {
        this.heap = new ArrayList<>();
    }

    public int size() {
        return this.heap.size();
    }

    public void insert(T element) {
        this.heap.add(element);
        this.heapifyUp(this.heap.size() - 1);
    }

    private void heapifyUp(int index) {
        while (isGreater(index, (index - 1) / 2)) {
            this.swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int a, int b) {
        T temp = this.heap.get(a);
        this.heap.set(a, this.heap.get(b));
        this.heap.set(b, temp);
    }

    private boolean isGreater(int a, int b) {
        return this.heap.get(a).compareTo(this.heap.get(b)) > 0;
    }

    public T peek() {
        if(this.heap.size() <= 0) {
            throw new IllegalArgumentException();
        }
        return this.heap.get(0);
    }

    public T pull() {
        if(this.heap.size() == 0) {
            throw new IllegalArgumentException();
        }

        T result = this.heap.get(0);
        this.heap.set(0, this.heap.get(this.size() - 1));
        this.hepifyDown(0);
        this.heap.remove(this.size() - 1);
        return result;
    }

    private void hepifyDown(int index) {
        while (index < this.size() / 2) {
            int child = 2 * index + 1;
            if(child + 1 < this.size() && this.isGreater(child + 1, child)) {
                child++;
            }

            if(this.isGreater(index, child)) {
                break;
            }
            this.swap(child, index);
            index = child;
        }
    }
}
