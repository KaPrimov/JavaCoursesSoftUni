package e_LinkedQueue;

public class LinkedQueue<E> {

    private int size;
    private QueueNode<E> head;
    private QueueNode<E> tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if(this.size == 0) {
            this.head = this.tail = new QueueNode<>(element);
        } else {
            QueueNode<E> newTail = new QueueNode<>(element);
            newTail.prevNode = this.tail;
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public E dequeue() {
        if (this.size() == 0) {
            throw new IllegalArgumentException();
        }
        E firstElement = this.head.value;
        this.head = this.head.nextNode;

        if(this.head != null) {
            this.head.prevNode = null;
        } else {
            this.tail = null;
        }

        this.size--;
        return firstElement;
    }

    public E[] toArray() {
        E[] arr = (E[]) new Object[this.size];
        QueueNode<E> currentNode = this.head;
        int index = 0;
        while (currentNode != null) {
            arr[index++] = currentNode.value;
            currentNode = currentNode.nextNode;
        }
        return arr;
    }

    private class QueueNode<E> {
        private E value;

        private QueueNode<E> nextNode;
        private QueueNode<E> prevNode;

        public QueueNode(E value) {
            this.value = value;
        }

        public E getValue() {
            return this.value;
        }

        private void setValue(E value) {
            this.value = value;
        }

        public QueueNode<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(QueueNode<E> nextNode) {
            this.nextNode = nextNode;
        }

        public QueueNode<E> getPrevNode() {
            return this.prevNode;
        }

        public void setPrevNode(QueueNode<E> prevNode) {
            this.prevNode = prevNode;
        }
    }
}