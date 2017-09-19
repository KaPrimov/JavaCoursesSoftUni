package tasks.orderedSet;

import java.util.function.Consumer;

public class OrderedSet<T extends Comparable<T>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private int nodesCount;

    public OrderedSet() {
    }

    private OrderedSet(Node root) {
        this.preOrderCopy(root);
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNodesCount() {
        return this.nodesCount;
    }

    public void insert(T value) {
        this.nodesCount++;
        this.root = this.insert(value, this.root);
        this.root.setColor(BLACK);
    }

    private Node insert(T value, Node node){
        if(node == null){
            node = new Node(value);
        }
        else if(value.compareTo(node.getValue())<0){
            node.setLeft(this.insert(value, node.getLeft()));
        }
        else if(value.compareTo(node.getValue())>0){
            node.setRight(this.insert(value, node.getRight()));
        }

        if (this.isRed(node.getRight()) && !this.isRed(node.getLeft())) {
            node = this.rotateLeft(node);
        }

        if(this.isRed(node.getLeft()) && this.isRed(node.getLeft().getLeft())) {
           node = this.rotateRight(node);
        }

        if(this.isRed(node.getLeft()) && this.isRed(node.getRight())) {
            this.flipColors(node);
        }

        node.childrenCount = 1 + this.count(node.getLeft()) + this.count(node.getRight());
        return node;
    }

    private int count(Node node){
        return node == null ? 0 : node.childrenCount;
    }

    public boolean contains(T value) {
        Node current = this.root;
        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return current != null;
    }

    public OrderedSet<T> search(T item) {
        Node current = this.root;
        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return new OrderedSet<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.getLeft(), consumer);
        consumer.accept(node.getValue());
        this.eachInOrder(node.getRight(), consumer);
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node min = this.root;
        Node parent = null;

        while (min.left != null) {
            parent = min;
            parent.childrenCount--;
            min = min.left;
        }

        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.left = min.right;
        }

        this.nodesCount--;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node max = this.root;
        Node parent = null;

        while (max.right != null) {
            parent = max;
            max = max.right;
        }

        if (parent == null) {
            this.root = this.root.left;
        } else {
            parent.right = max.left;
        }
    }

    public void delete(T key) {
        this.root = deleteRecursive(this.root, key);
    }

    private Node deleteRecursive(Node root, T key) {
        if (root == null) {
            return root;
        }

        if (key.compareTo(root.value) < 0) {
            root.left = deleteRecursive(root.left, key);
        }
        else if (key.compareTo(root.value) > 0) {
            root.right = deleteRecursive(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.value = minValue(root.right);

            root.right = deleteRecursive(root.right, root.value);
        }

        return root;
    }

    public T select(int rank) {
        Node node = this.select(rank, this.root);
        if (node == null) {
            throw new IllegalArgumentException("ERROR");
        }

        return node.value;
    }

    private Node select(int rank, Node node) {
        if (node == null) {
            return null;
        }

        int leftCount = this.size(node.left);
        if (leftCount == rank) {
            return node;
        }

        if (leftCount > rank) {
            return this.select(rank, node.left);
        } else {
            return this.select(rank - (leftCount + 1), node.right);
        }
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }

        return node.childrenCount;
    }

    private boolean isRed(Node node) {
        return node != null && node.getColor() == RED;
    }

    private Node rotateLeft(Node node) {
        Node temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);

        temp.childrenCount = node.childrenCount;
        temp.setColor(node.getColor());
        node.setColor(RED);

        node.childrenCount = 1 + this.count(node.getLeft()) + this.count(node.getRight());

        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);

        temp.setColor(node.getColor());
        node.setColor(RED);

        temp.setChildrenCount(node.childrenCount);
        node.childrenCount = 1 + this.count(node.getLeft()) + this.count(node.getRight());

        return temp;
    }

    private void flipColors(Node node) {
        node.setColor(RED);
        if (node.getLeft() != null){
            node.getLeft().setColor(BLACK);
        }
        if (node.getRight() != null){
            node.getRight().setColor(BLACK);
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;
        private boolean color;

        private int childrenCount;

        public Node(T value) {
            this(value, RED);
        }

        public Node(T value, boolean color) {
            this.value = value;
            this.childrenCount = 1;
            this.color = color;
        }

        public T getValue() {
            return this.value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public void setChildrenCount(int childrenCount) {
            this.childrenCount = childrenCount;
        }

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}