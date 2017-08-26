package d_LinkedStack;

public class LinkedStack<E> {

    private Node<E> firstNode;
    private int size;

    public final int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        Node<E> newNode = new Node<>(element, this.firstNode);
        this.firstNode = newNode;
        this.size++;
//        if(this.size == 0) {
//            this.firstNode = newNode;
//        } else {
//            Node<E> tempNode = this.firstNode;
//            this.firstNode = new Node<E>();
//            this.firstNode.nextNode = tempNode;
//        }
    }

    public E pop() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        E value = this.firstNode.value;
        this.firstNode = this.firstNode.nextNode;
        this.size--;
        return value;
    }

    public E[] toArray() {

        Node currentNode = this.firstNode;
        E[] resultArr = (E[]) new Object[this.size];
        int index = 0;
        while (currentNode != null) {
            resultArr[index++] = (E) currentNode.value;
            currentNode = currentNode.nextNode;
        }
        return resultArr;
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this(value);
            this.nextNode = nextNode;
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}