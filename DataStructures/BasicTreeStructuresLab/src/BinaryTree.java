import java.util.function.Consumer;

public class BinaryTree<T> {

    private final T value;
    private final BinaryTree<T> leftChild;
    private final BinaryTree<T> rightChild;

    public static class Builder<T> {
        //required
        private T value;

        //optional
        private BinaryTree<T> leftChild = null;
        private BinaryTree<T> rightChild = null;

        public Builder(T value) {
            this.value = value;
        }

        public Builder leftChild(BinaryTree<T> leftChild) {
            this.leftChild = leftChild;
            return this;
        }

        public Builder rightChild(BinaryTree<T> rightChild) {
            this.rightChild = rightChild;
            return this;
        }

        public BinaryTree<T> build() {
            return new BinaryTree<>(this);
        }
    }

    private BinaryTree(Builder<T> builder) {
        this.value = builder.value;
        this.leftChild = builder.leftChild;
        this.rightChild = builder.rightChild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        //Pre-order ==  root, left, right
        builder.append(new String(new char[2 * indent]).replace("\0", " "));
        builder.append(this.value);
        builder.append("\n");
        if(this.leftChild != null) {
            this.leftChild.printIndentedPreOrder(indent + 1, builder);
        }
        if(this.rightChild != null) {
            this.rightChild.printIndentedPreOrder(indent + 1, builder);
        }
        return builder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {
        // In-order == left, root, right
        if(this.leftChild != null) {
            this.leftChild.eachInOrder(consumer);
        }

        consumer.accept(this.value);

        if(this.rightChild != null) {
            this.rightChild.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        //Post-order == left, right, root
        if(this.leftChild != null) {
            this.leftChild.eachPostOrder(consumer);
        }

        if(this.rightChild != null) {
            this.rightChild.eachPostOrder(consumer);
        }

        consumer.accept(this.value);
    }
}
