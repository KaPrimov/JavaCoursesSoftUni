import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Tree<T> {
    private T value;
    private Tree<T> parent;
    private List<Tree<T>> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.setChildren(children);
    }

    private void setChildren(Tree<T>... children) {
        this.children = new ArrayList<>();
        for (Tree<T> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    public final void addChild(Tree<T> child) {
        this.children.add(child);
    }

    public final void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public final Tree<T> getParent() {
        return this.parent;
    }

    public final T getValue() {
        return value;
    }

    public String print(int indent, StringBuilder builder) {
        builder.append(new String(new char[indent]).replace("\0", " "));
        builder.append(this.value);
        builder.append(System.lineSeparator());
        for (Tree<T> child : this.children) {
            child.print(indent + 2, builder);
        }
        return builder.toString();
    }

    public String orderDFS() {
        StringBuilder result = new StringBuilder();
        this.DFS(this, result);
        return result.toString();
    }

    private void DFS(Tree<T> tree, StringBuilder result) {
        for (Tree<T> child : tree.children) {
            child.DFS(child, result);
        }
        result.append(this.value).append(" ");
    }

    public Collection<Tree<T>> getChildren() {
        return Collections.unmodifiableCollection(this.children);
    }
}
