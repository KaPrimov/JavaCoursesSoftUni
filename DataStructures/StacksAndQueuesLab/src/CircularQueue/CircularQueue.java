package CircularQueue;

public class CircularQueue<E> {

    private static final int DEFAULT_CAPACITY = 16;

    private int size;
    private E[] elements;
    private int startIndex = 0;
    private int endIndex = 0;

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularQueue(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
    }

    public final int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if(this.size >= this.elements.length) {
            this.grow();
        }
        this.elements[this.endIndex] = element;
        this.endIndex = (this.endIndex + 1) % this.elements.length;
        this.size++;
    }

    private void grow() {
        E[] newElements = (E[]) new Object[2 * this.elements.length];
        this.copyAllElements(newElements);
        this.startIndex = 0;
        this.endIndex = this.size;
        this.elements = newElements;
    }

    private void copyAllElements(E[] resultArr) {
        int sourceIndex = this.startIndex;
        int destinationIndex = 0;
        for (int i = 0; i < this.size; i++) {
            resultArr[destinationIndex] = this.elements[sourceIndex];
            sourceIndex = (sourceIndex + 1) % this.elements.length;
            destinationIndex++;
        }
    }

    public E dequeue() {
        if(this.size == 0) {
            throw new IllegalArgumentException("The queue is empty!");
        }
        E result = this.elements[this.startIndex];
        this.startIndex = (this.startIndex + 1) % this.elements.length;
        this.size--;
        return result;
    }

    public E[] toArray() {
        E[] resultArr = (E[]) new Object[this.size];
        this.copyAllElements(resultArr);
        return resultArr;
    }

}
