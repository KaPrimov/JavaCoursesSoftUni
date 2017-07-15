package customLinkedList;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList<T> implements Iterable<T>{

    private static class ListNode<T> {

        private T value;
        private ListNode<T> nextNode;

        ListNode(T value){
            this.value = value;
            this.nextNode = null;
        }
    }

    private ListNode<T> head;
    private ListNode<T> tail;
    private int count;

    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public void add(T element){
        if (this.count == 0){
            this.head = this.tail = new ListNode<>(element);
        } else {
            ListNode<T> newTail = new ListNode<>(element);
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }

        this.count++;
    }

    public boolean remove(T element){

        ListNode<T> current = this.head;
        ListNode<T> prev = null;
        while (current != null){
            if(current.value == element){
                if(current == this.tail && current == this.head){
                    this.head = this.tail = null;
                }
                else if(current == this.tail){
                    this.tail = prev;
                    this.tail.nextNode = null;
                }
                else if(current == this.head){
                    this.head = current.nextNode;
                }
                else {
                    prev.nextNode = current.nextNode;
                }

                this.count--;
                return true;
            }
            prev = current;
            current = current.nextNode;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        if (this.count == 0){
            return Collections.emptyIterator();
        }
        return new Iterator<T>() {
            private ListNode<T> currentNode = null;

            @Override
            public boolean hasNext() {
                return this.currentNode != tail;
            }

            @Override
            public T next() {
                if (currentNode == null) {
                    currentNode = head;
                    return currentNode.value;
                }
                if (currentNode.nextNode == null) {
                    throw new NoSuchElementException();
                }
                currentNode = currentNode.nextNode;
                return currentNode.value;
            }
        };
    }
}