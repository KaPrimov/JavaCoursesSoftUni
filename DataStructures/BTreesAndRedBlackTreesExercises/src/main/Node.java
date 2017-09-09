package main;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T value;

    private Node<T> parent;
    private List<Node<T>> children;

    public Node(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Node(T value, Node<T> parent) {
        this(value);
        this.parent = parent;
    }

    public void addChild(Node<T> child) {
        this.children.add(child);
    }

    public T getValue() {
        return value;
    }

    public Node<T> getParent() {
        return parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }
}
