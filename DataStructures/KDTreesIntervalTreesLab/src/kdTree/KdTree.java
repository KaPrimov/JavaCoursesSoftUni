package kdTree;

import java.util.function.Consumer;

public class KdTree {

    public class Node {

        private Point2D point2D;
        private Node left;
        private Node right;

        public Node(Point2D point) {
            this.setPoint2D(point);
        }

        public Point2D getPoint2D() {
            return this.point2D;
        }

        public void setPoint2D(Point2D point2D) {
            this.point2D = point2D;
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
    }

    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public boolean contains(Point2D point) {
        Node node = this.contains(this.root, point, 0);
        return node != null;
    }

    private Node contains(Node node, Point2D point, int depth) {
        if(node == null) {
            return null;
        }
        int cmp = this.compare(point, node.point2D, depth);
        if(cmp == 0) {
            return node;
        }

        if(cmp < 0) {
            return this.contains(node.left, point, depth + 1);
        } else {
            return this.contains(node.right, point, depth + 1);
        }
    }

    public void insert(Point2D point) {
        this.root = this.insert(this.root, point, 0);
    }

    private Node insert(Node node, Point2D point, int depth) {
        if (node == null) {
            return new Node(point);
        }

        int cmp = compare(point, node.getPoint2D(), depth);
        if (cmp < 0) {
            node.setLeft(this.insert(node.getLeft(), point, depth + 1));
        } else if (cmp > 0) {
            node.setRight(this.insert(node.getRight(), point, depth + 1));
        }

        return node;
    }

    private int compare(Point2D newPoint, Point2D currentPoint, int depth) {
        int result = 0;
        if (depth % 2 == 0) {
            result = Double.compare(newPoint.getX(), currentPoint.getX());
            if(result == 0) {
                result  = Double.compare(newPoint.getY(), currentPoint.getY());
            }
        } else {
            result  = Double.compare(newPoint.getY(), currentPoint.getY());
            if(result == 0) {
                result = Double.compare(newPoint.getX(), currentPoint.getX());
            }
        }

        return result;
    }

    public void eachInOrder(Consumer<Point2D> consumer) {
       this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<Point2D> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.getLeft(), consumer);
        consumer.accept(node.getPoint2D());
        this.eachInOrder(node.getRight(), consumer);
    }
}
