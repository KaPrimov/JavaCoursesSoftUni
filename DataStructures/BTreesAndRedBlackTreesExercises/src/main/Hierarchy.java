package main;

import java.lang.reflect.Array;
import java.util.*;

public class Hierarchy<T> implements IHierarchy<T> {

    private Node<T> root;
    private Map<T, Node<T>> nodesByValue;

    public Hierarchy(T element){
        this.root = new Node<>(element);
        this.nodesByValue = new LinkedHashMap<>();
        this.nodesByValue.put(element, this.root);
    }

    public void add(T parent, T child){
        if(!this.nodesByValue.containsKey(parent)) {
            throw new IllegalArgumentException("Can not add. Parent does not exist.");
        }
        if(this.nodesByValue.containsKey(child)) {
            throw new IllegalArgumentException("Child already exists");
        }
        Node<T> parentNode = this.nodesByValue.get(parent);
        Node<T> childNode = new Node<>(child, parentNode);
        parentNode.addChild(childNode);
        this.nodesByValue.put(child, childNode);
    }

    public int getCount() {
        return this.nodesByValue.size();
    }

    public void remove(T element){
        if(!this.nodesByValue.containsKey(element)) {
            throw new IllegalArgumentException("The element can not be removed. It does not exist");
        }
        Node<T> toBeRemoved = nodesByValue.get(element);
        if(toBeRemoved.getParent() == null) {
            throw new IllegalStateException("The parent can not be removed");
        }

        Node<T> parent = toBeRemoved.getParent();

        List<Node<T>> childrenToBeReassigned = toBeRemoved.getChildren();
        for (Node<T> child : childrenToBeReassigned) {
            parent.addChild(child);

            child.setParent(parent);
        }
        parent.getChildren().remove(toBeRemoved);
        toBeRemoved.setChildren(null);

        this.nodesByValue.remove(element);
    }

    public boolean contains(T element) {
        return this.nodesByValue.containsKey(element);
    }

    public T getParent(T element){
        if (!this.nodesByValue.containsKey(element)) {
            throw new IllegalArgumentException("The element does not exist!");
        }
        return this.nodesByValue.get(element).getParent().getValue();
    }

    public Iterable<T> getChildren(T element){
        if (!this.nodesByValue.containsKey(element)) {
            throw new IllegalArgumentException("The element does not exist!");
        }
        List<T> values = new ArrayList<>();
        for (Node<T> tNode : this.nodesByValue.get(element).getChildren()) {
            values.add(tNode.getValue());
        }
        return values;
    }

    public Iterable<T> getCommonElements(IHierarchy<T> other){
        List<T> commonElements = new ArrayList<>();
        for (T currentElement : nodesByValue.keySet()) {
            if(other.contains(currentElement)) {
                commonElements.add(currentElement);
            }
        }
        return commonElements;
    }

    @Override
    public Iterator<T> iterator() {
        return new BFSIterator();
    }

    private class BFSIterator implements Iterator<T> {

        private Deque<T> bfsOrderedNodes = new ArrayDeque<>();

        BFSIterator() {
            Deque<Node<T>> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node<T> current = queue.poll();
                this.bfsOrderedNodes.add(current.getValue());
                for (Node<T> tNode : current.getChildren()) {
                    queue.add(tNode);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !this.bfsOrderedNodes.isEmpty();
        }

        @Override
        public T next() {
            return this.bfsOrderedNodes.poll();
        }
    }

    private T getDefaultValue(Class<T> clazz) {
        return (T) Array.get(Array.newInstance(clazz, 1), 0);
    }
}
