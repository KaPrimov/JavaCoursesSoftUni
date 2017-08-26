package LinkedList;

import java.util.Iterator;
import java.util.Objects;

public class LinkedList<E> implements Iterable<E> {

    private class Node {
        private final E value;
        private Node next;

        public Node(E value) {
            this.value = value;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        Node old = head;

        this.head = new Node(item);
        this.head.next = old;

        if (isEmpty()) {
            this.tail = this.head;
        }

        this.size++;
    }

    public void addLast(E item) {
        Node old = tail;
        this.tail = new Node(item);

        if (isEmpty()) {
            this.tail = this.head;
        } else {
            old.next = this.tail;
        }

        this.size++;
    }

    public E removeFirst() {
        if(this.isEmpty()) {
            throw new UnsupportedOperationException();
        }

        E item = this.head.value;

        this.head = this.head.next;

        this.size--;
        if (this.isEmpty()) {
            this.tail = this.head = null;
        }

        return item;
    }

    public E removeLast() {
        if(this.isEmpty()) {
            throw new UnsupportedOperationException();
        }

        E item = this.tail.value;

        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            Node newTail = this.getSecondToLast();
            newTail.next = null;
            this.tail = newTail;
        }

        this.size--;

        return item;
    }

    private Node getSecondToLast() {
        Node currentNode = this.head;
        while (!currentNode.next.equals(this.tail)) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node currentNode;

        public LinkedListIterator() {
            this.currentNode = tail;
        }

        @Override
        public boolean hasNext() {
            return Objects.nonNull(this.currentNode);
        }

        @Override
        public E next() {
            E value = this.currentNode.value;
            this.currentNode = this.currentNode.next;
            return value;
        }
    }
}
