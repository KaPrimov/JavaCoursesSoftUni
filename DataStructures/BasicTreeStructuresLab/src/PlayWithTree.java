public class PlayWithTree {

    public static void main(String[] args) {
//        Tree<Integer> tree =
//                new Tree<>(7,
//                        new Tree<>(19,
//                                new Tree<>(1),
//                                new Tree<>(12),
//                                new Tree<>(31)),
//                        new Tree<>(21),
//                        new Tree<>(14,
//                                new Tree<>(23),
//                                new Tree<>(6)));
//
//        System.out.println("Tree (indented):");
//        String output = tree.print(0, new StringBuilder());
//        System.out.println(output);
//
//        System.out.println("Tree nodes:");
//        tree.each(e -> System.out.print(" " + e));
//        System.out.println();

        System.out.println();
        BinaryTree<String> binaryTree = new BinaryTree.Builder<>("*")
                .leftChild(new BinaryTree.Builder<>("+")
                        .leftChild(new BinaryTree.Builder<>("3").build())
                        .rightChild(new BinaryTree.Builder<>("2").build())
                        .build())
                .rightChild(new BinaryTree.Builder<>("-")
                        .leftChild(new BinaryTree.Builder<>("9").build())
                        .rightChild(new BinaryTree.Builder<>("6").build())
                        .build())
                .build();

        System.out.println("Binary tree (indented, pre-order):");
        String output = binaryTree.printIndentedPreOrder(0, new StringBuilder());
        System.out.println(output);

        System.out.println("Binary tree nodes (in-order):");
        binaryTree.eachInOrder(e -> System.out.print(" " + e));
        System.out.println();

        System.out.println("Binary tree nodes (post-order):");
        binaryTree.eachPostOrder(e -> System.out.print(" " + e));
        System.out.println();
    }
}
