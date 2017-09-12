import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        this.root = this.insert(this.root, item);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    private Node<T> insert(Node<T> node, T item) {
        if (node == null) {
            return new Node<>(item);
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            node.left = this.insert(node.left, item);
        } else if (cmp > 0) {
            node.right = this.insert(node.right, item);
        }


        node = this.balance(node);
        updateHeight(node);
        return node;
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            return search(node.left, item);
        } else if (cmp > 0) {
            return search(node.right, item);
        }

        return node;
    }

    public void delete(T item) {
        this.root = delete(this.root, item);
    }

    private Node<T> delete(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            node.left = delete(node.left, item);
        } else if (cmp > 0) {
            node.right = delete(node.right, item);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<T> minNode = this.findSmallestNode(node.right);
                minNode.right = this.deleteMin(node.right);
                minNode.left = node.left;
                node = minNode;
            }
        }
        node = this.balance(node);
        this.updateHeight(node);
        return node;
    }

    public void deleteMin() {
        if (this.root == null) {
            return;
        }
        this.root = this.deleteMin(this.root);
    }

    private Node<T> deleteMin(Node<T> current) {

        if (current.left == null) {
            return current.right;
        }

        current.left = deleteMin(current.left);
        current = this.balance(current);
        this.updateHeight(current);
        return current;
    }

    // BONUS
    public void deleteMax() {
        if (this.root == null) {
            return;
        }
        this.root = this.deleteMax(this.root);
    }

    private Node<T> deleteMax(Node<T> current) {
        if (current.right == null) {
            return current.left;
        }

        current.right = deleteMin(current.right);
        current = this.balance(current);
        this.updateHeight(current);
        return current;
    }

    private Node<T> balance(Node<T> node) {
        // right rotation
        int balance = this.getHeight(node.left) - this.getHeight(node.right);

        if (balance > 1) {
            int childBalance = this.getHeight(node.left.left) - this.getHeight(node.left.right);
            if (childBalance < 0) {
                node.left = this.rotateLeft(node.left);
            }
            return this.rotateRight(node);
        } else if (balance < -1) {
            int childBalance = this.getHeight(node.right.left) - this.getHeight(node.right.right);
            if (childBalance > 0) {
                node.right = this.rotateRight(node.right);
            }
            return this.rotateLeft(node);
        }
        this.updateHeight(node);
        return node;
    }

    private void updateHeight(Node<T> node) {
        node.height = Math.max(this.getHeight(node.left), this.getHeight(node.right)) + 1;
    }


    private int getHeight(Node<T> node) {
        return node == null ? 0 : node.height;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> tempLeft = node.left;
        node.left = tempLeft.right;
        tempLeft.right = node;

        updateHeight(node);

        return tempLeft;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> tempRight = node.right;
        node.right = tempRight.left;
        tempRight.left = node;

        this.updateHeight(node);

        return tempRight;
    }

    private Node<T> findSmallestNode(Node<T> element) {
        if(element == null) {
            return null;
        }
        if (element.left == null) {
            return element;
        }
        return findSmallestNode(element.left);
    }

    private Node<T> findBiggestNode(Node<T> element) {
        if(element == null) {
            return null;
        }
        if (element.right == null) {
            return element;
        }
        return findSmallestNode(element.right);
    }
}
