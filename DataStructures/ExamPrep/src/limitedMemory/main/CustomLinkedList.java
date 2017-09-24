package limitedMemory.main;

import java.util.Iterator;
import java.util.Objects;

public class CustomLinkedList<K, V> implements Iterable<Pair<K, V>> {



    private Pair<K, V> head;
    private Pair<K, V> tail;

    private int count;

    public int size() {
        return count;
    }

    public void addFirst(Pair<K, V> node) {
        if (this.count == 0) {
            this.head = this.tail = node;
        } else {
            node.nextNode = this.head;
            this.head.prevNode = node;
            this.head = node;
        }
        this.count++;
    }

    public void addLast(K key, V value) {
        if (this.count == 0) {
            this.head = this.tail = new Pair<>(key, value);
        } else {
            Pair<K, V> newTail = new Pair<>(key, value);
            newTail.prevNode = this.tail;
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }
        this.count++;
    }

    public void removeFirst() {
        if (this.count == 0) {
            throw new UnsupportedOperationException("List is empty");
        }

        this.head = this.head.nextNode;
        if(this.head != null) {
            this.head.prevNode = null;
        } else {
            this.tail = null;
        }

        this.count--;
    }

    public Pair<K, V> removeLast() {
        if (this.count == 0) {
            throw new UnsupportedOperationException("List is empty");
        }
        Pair<K, V> tempNode = this.tail;
        this.tail = this.tail.prevNode;

        if (this.tail != null) {
            this.tail.nextNode = null;
        } else {
            this.head = null;
        }

        this.count--;
        return tempNode;
    }

    public void remove(Pair node) {
        if(node.prevNode == null) {
            this.head = this.head.nextNode;
        } else {
            node.prevNode.nextNode = node.nextNode;
        }
        this.count--;
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Pair<K, V>> {

        private Pair<K, V> currentNode = head;

        @Override
        public boolean hasNext() {
            return !Objects.isNull(currentNode);
        }

        @Override
        public Pair<K, V> next() {
            V value = currentNode.getValue();
            K key = currentNode.getKey();
            Pair<K, V> kvPair = new Pair<>(key, value);
            currentNode = currentNode.nextNode;
            return kvPair;
        }
    }
}