import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int count = 0;

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
            this.count++;
            return;
        }

        Node parent = null;
        Node current = this.root;

        while (current != null) {
            parent = current;
            if (current.value == value) {
                return;
            } else if (current.value.compareTo(value) > 0) {
                parent.childrenCount++;
                current = current.left;
            } else if (current.value.compareTo(value) < 0) {
                parent.childrenCount++;
                current = current.right;
            }
        }
        if (parent.value.compareTo(value) > 0) {
            parent.left = new Node(value);
        } else if (parent.value.compareTo(value) < 0) {
            parent.right = new Node(value);
        }
        this.count++;
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
            throw new IllegalArgumentException();
        }

        Node parent = null;
        Node current = this.root;

        while (current.left != null) {
            parent = current;
            parent.childrenCount--;
            current = current.left;
        }

        if (parent == null) {
            this.root = current.right;
        } else {
            parent.left = current.right;
        }
        this.count--;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }

        Node parent = null;
        Node current = this.root;

        while (current.right != null) {
            parent = current;
            parent.childrenCount--;
            current = current.right;
        }

        if (parent == null) {
            this.root = current.left;
        } else {
            parent.right = current.left;
        }
        this.count--;
    }

    public int count() {
        return this.count;
    }

    public T ceil(T element) {

        Node parent = null;
        Node current = this.root;

        while (current != null) {
            if (current.value.compareTo(element) > 0) {
                parent = current;
                current = current.left;
            } else if(current.value.compareTo(element) < 0) {
                if (parent != null && current.right != null && current.right.value.compareTo(element) < 0) {
                    return parent.value;
                }
                parent = current;
                current = current.right;
            } else {
                return current.value;
            }
        }

        if (parent != null && parent.value.compareTo(element) >= 0) {
            return parent.value;
        }

        return null;
    }

    public T floor(T element) {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }

        Node parent = null;
        Node current = this.root;

        while (current != null) {
            if(current.value.compareTo(element) > 0) {
                if(parent != null && parent.value.compareTo(element) < 0) {
                    return parent.value;
                }
                parent = current;
                current = current.left;
            } else if (current.value.compareTo(element) < 0) {
                parent = current;
                current = current.right;
            } else {
                return current.value;
            }
        }
        if (parent != null && parent.getValue().compareTo(element) <= 0) {
            return parent.getValue();
        }
        return null;
    }

    public void delete(T item) {
        if (this.getRoot() == null) {
            return;
        }
        Node parent = null;
        Node forDeleting = this.root;
        while (forDeleting != null) {
            forDeleting.childrenCount--;
            if (forDeleting.getValue() == item) break;
            else if (forDeleting.getValue().compareTo(item) > 0) {
                parent = forDeleting;
                forDeleting = forDeleting.getLeft();
            } else if (forDeleting.getValue().compareTo(item) < 0) {
                parent = forDeleting;
                forDeleting = forDeleting.getRight();
            }
        }

        if (forDeleting == null) {
            return;
        }

        if (forDeleting.getLeft() == null && forDeleting.getRight() == null) {
            changeParent(parent, forDeleting, null);
            if (parent == null) this.root.setValue(null);
            return;
        }
        if (forDeleting.getRight() == null) {
            forDeleting.getLeft().childrenCount = forDeleting.childrenCount - 1;
            changeParent(parent, forDeleting, forDeleting.getLeft());
            return;
        }
        if (forDeleting.getRight().getLeft() == null) {
            forDeleting.getRight().childrenCount = forDeleting.childrenCount - 1;
            forDeleting.getRight().setLeft(forDeleting.getLeft());
            changeParent(parent, forDeleting, forDeleting.getRight());
            return;
        }
        Node prev = forDeleting.getRight();
        Node crnt = prev.getLeft();
        while (crnt.getLeft() != null) {
            crnt.childrenCount--;
            prev = crnt;
            crnt = crnt.getLeft();
        }
        prev.setLeft(null);
        crnt.childrenCount = forDeleting.childrenCount - 1;
        crnt.setLeft(forDeleting.getLeft());
        crnt.setRight(forDeleting.getRight());
        changeParent(parent, forDeleting, crnt);
    }

    private void changeParent(Node parent, Node forDeleting, Node newParent) {
        if (parent == null) this.root = newParent;
        else if (parent.getLeft() == forDeleting) parent.setLeft(newParent);
        else if (parent.getRight() == forDeleting) parent.setRight(newParent);
    }

    public int rank(T item) {
        return rank(this.root, item);
    }

    private int rank(Node node, T element) {

        if (node == null) {
            return 0;
        }

        int comparisionResult = element.compareTo(node.value);

        if(comparisionResult < 0) {
            return this.rank(node.left, element);
        } else if(comparisionResult > 0) {
            return 1 + this.getChildrenCount(node.left) + this.rank(node.right, element);
        }

        return this.getChildrenCount(node.left);
    }

    private int getChildrenCount(Node node) {
        if (node == null) {
            return 0;
        }

        return node.childrenCount;
    }

    public T select(int n) {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }

        Node node = this.root;
        while (node != null) {
            if(this.rank(node.value) > n) {
                node = node.left;
            } else if (this.rank(node.value) < n){
                node = node.right;
            } else {
                break;
            }
        }

        return node.value;
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        private int childrenCount;

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
            this.childrenCount = 1;
        }

    }


}

