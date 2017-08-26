package c_ArrayBasedStack;

public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    public ArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        if(this.size >= this.elements.length) {
            this.grow();
        }
        this.elements[this.size] = element;
        this.size++;
    }

    public T pop() {
        if (this.size() == 0) {
            throw new IllegalArgumentException();
        }
        
        T element = this.elements[--this.size];
        this.elements[this.size] = null;
        return element;
    }

    public T[] toArray() {
        T[] resultElements = (T[]) new Object[this.size];
        int destinationIndex = 0;
        for (int i = this.size - 1; i >= 0; i--) {
            resultElements[destinationIndex++] = this.elements[i];
        }
        return resultElements;
    }

    private void grow() {
        T[] newElementsArr = (T[]) new Object[this.size() * 2];

        for (int a = 0; a < this.size(); a++) {
            newElementsArr[a] = elements[a];
        }
        this.elements = newElementsArr;
    }

}