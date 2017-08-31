import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node root) {
        this.copy(root);
    }

    private void copy(Node current) {
        if (current == null) {
            return;
        }

        this.insert(current.value);
        this.copy(current.left);
        this.copy(current.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = this.root;

        while (current != null) {
            parent = current;
            if (current.value == value) {
                return;
            } else if (current.value.compareTo(value) > 0) {
                current = current.left;
            } else if (current.value.compareTo(value) < 0) {
                current = current.right;
            }
        }
        if (parent.value.compareTo(value) > 0) {
            parent.left = new Node(value);
        } else if (parent.value.compareTo(value) < 0) {
            parent.right = new Node(value);
        }
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

    public BinarySearchTree<T> search(T item) {
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

        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        if (this.root.left != null) {
            consumer.accept(this.root.left.value);
        }

        consumer.accept(this.root.value);

        if (this.root.right != null) {
            consumer.accept(this.root.right.value);
        }
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new ArrayDeque<>();

        this.range(from, to, queue, this.root);

        return queue;
    }

    private void range(T from, T to, Deque<T> queue, Node node) {
        if (node == null) {
            return;
        }

        int nodeInLowerRange = from.compareTo(node.value);
        int nodeInHigherRange = to.compareTo(node.value);

        if (nodeInLowerRange < 0) {
            range(from, to, queue, node.left);
        } else if(nodeInLowerRange <= 0 && nodeInHigherRange >= 0) {
            queue.addFirst(node.value);
        } else if (nodeInHigherRange > 0) {
            range(from, to, queue, node.right);
        }
    }

    public void deleteMin() {
        if (this.root == null) {
            return;
        }

        Node parent = null;
        Node current = this.root;

        while (current.left != null) {
            parent = current;
            current = current.left;
        }

        if (parent == null) {
            this.root = current.right;
        } else {
            parent.left = current.right;
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node(T value) {
            this.value = value;
        }

    }

    public static void main(String[] args) {
        Integer lowerRange = 10;
        Integer current = 8;

        System.out.println(lowerRange.compareTo(current));
    }
}

