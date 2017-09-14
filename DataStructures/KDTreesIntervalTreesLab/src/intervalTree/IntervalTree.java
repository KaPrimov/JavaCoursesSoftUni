package intervalTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IntervalTree {

    private class Node {

        private Interval interval;
        private double max;
        private Node right;
        private Node left;

        public Node(Interval interval) {
            this.interval = interval;
            this.max = interval.getHi();
        }
    }

    private Node root;

    public void insert(double lo, double hi) {
        this.root = this.insert(this.root, lo, hi);
    }

    public void eachInOrder(Consumer<Interval> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    public Interval searchAny(double lo, double hi) {
        Node node = this.root;
        while (node != null && !node.interval.intersects(lo, hi)) {
            if(node.left != null && node.left.max > lo) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) {
            return null;
        }
        return node.interval;
    }

    public Iterable<Interval> searchAll(double lo, double hi) {
        List<Interval> result = new ArrayList<>();
        this.searchAll(this.root, lo, hi, result);

        return result;
    }

    private void searchAll(Node current, double lo, double hi, List<Interval> intervalList) {
        if(current == null) {
            return;
        }

        boolean goLeft = current.left != null && current.left.max >= lo;
        boolean goRight = current.right != null && current.right.interval.getLo() < hi;

        if(goLeft) {
            searchAll(current.left, lo, hi, intervalList);
        }
        if(current.interval.intersects(lo, hi)) {
            intervalList.add(current.interval);
        }
        if (goRight) {
            searchAll(current.right, lo, hi, intervalList);
        }
    }

    private void eachInOrder(Node node, Consumer<Interval> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.interval);
        this.eachInOrder(node.right, consumer);
    }

    private Node insert(Node node, double lo, double hi) {
        if (node == null) {
            return new Node(new Interval(lo, hi));
        }

        int cmp = Double.compare(lo, node.interval.getLo());
        if (cmp < 0) {
            node.left = insert(node.left, lo, hi);
        } else if (cmp > 0) {
            node.right = insert(node.right, lo, hi);
        }
        updateMax(node);
        return node;
    }

    private void updateMax(Node node) {
        Node maxChild = getMaxNode(node.left, node.right);
        node.max = getMaxNode(node, maxChild).max;
    }

    private Node getMaxNode(Node left, Node right) {
        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return left.max > right.max ? left : right;
    }
}
