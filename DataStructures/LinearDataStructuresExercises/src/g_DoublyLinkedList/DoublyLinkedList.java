package g_DoublyLinkedList;

import java.util.Iterator;
import java.util.Objects;

public class DoublyLinkedList<T> implements Iterable<T> {

    private class ListNode<T> {
        public T value;
        public ListNode<T> nextNode;
        public ListNode<T> prevNode;

        public ListNode(T value) {
            this.value = value;
        }
    }

    private ListNode<T> head;
    private ListNode<T> tail;

    private int count;

    public int size() {
        return count;
    }

    public void addFirst(T element) {
        if (this.count == 0) {
            this.head = this.tail = new ListNode<>(element);
        } else {
            ListNode<T> newHead = new ListNode<>(element);
            newHead.nextNode = this.head;
            this.head.prevNode = newHead;
            this.head = newHead;
        }
        this.count++;
    }

    public void addLast(T element) {
        if (this.count == 0) {
            this.head = this.tail = new ListNode<>(element);
        } else {
            ListNode<T> newTail = new ListNode<>(element);
            newTail.prevNode = this.tail;
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }
        this.count++;
    }

    public T removeFirst() {
        if (this.count == 0) {
            throw new UnsupportedOperationException("List is empty");
        }

        T firstElement = this.head.value;
        this.head = this.head.nextNode;
        if(this.head != null) {
            this.head.prevNode = null;
        } else {
            this.tail = null;
        }

        this.count--;
        return firstElement;
    }

    public T removeLast() {
        if (this.count == 0) {
            throw new UnsupportedOperationException("List is empty");
        }

        T lastElement = this.tail.value;
        this.tail = this.tail.prevNode;

        if (this.tail != null) {
            this.tail.nextNode = null;
        } else {
            this.head = null;
        }

        this.count--;
        return lastElement;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arr = (T[]) new Object[this.count];
        ListNode<T> currentNode = this.head;
        int index = 0;
        while (currentNode != null) {
            arr[index++] = currentNode.value;
            currentNode = currentNode.nextNode;
        }
        return arr;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private ListNode<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return !Objects.isNull(currentNode);
        }

        @Override
        public T next() {
            T value = currentNode.value;
            currentNode = currentNode.nextNode;
            return value;
        }
    }
}
