import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    private T value;
    private List<Tree<T>> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = Arrays.asList(children);
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {
        builder.append(new String(new char[indent]).replace("\0", " "));
        builder.append(this.value);
        builder.append("\n");
        for (Tree<T> child : this.children) {
            child.print(indent + 2, builder);
        }
        return builder.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree<T> child : children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> order = new ArrayList<>();
        this.DFS(this, order);
        return order;
    }

    private void DFS(Tree<T> tree, List<T> order) {
        for (Tree<T> t : tree.children) {
            this.DFS(t, order);
        }
        order.add(tree.value);
    }

    public Iterable<T> orderBFS() {
        List<T> result = new ArrayList<>();
        Deque<Tree<T>> queue = new ArrayDeque<>();

        queue.add(this);
        while (!queue.isEmpty()) {
            Tree<T> element = queue.poll();
            result.add(element.value);
            queue.addAll(element.children);
        }
        return result;
    }

}